package org.murtools

import groovy.util.logging.Slf4j;
import groovy.util.Proxy
import org.gradle.api.DefaultTask
import org.gradle.api.file.FileCollection
import org.gradle.api.tasks.TaskAction

@Slf4j
class PrecompileTask extends DefaultTask {
	final def DEFAULT_SRC_DIR = 'src/main/webapp'
	final def DEFAULT_WORKDIR = 'build/scalate-out/work'
	final def DEFAULT_CLASSES = 'build/classes/main'
	
	File templateSrcDir
	File workingDirectory
	File targetDirectory 
	List<String> templates
	String contextClass
	String bootClassName
	
	@TaskAction
	protected void start() {
		def classpathURLs = convertURL(project.files(project.configurations.compile, targetDirectory ?: DEFAULT_CLASSES))
		def precompiler = loadPrecompiler(classpathURLs)
		precompiler.invokeMethod('sources_$eq', templateSrcDir ?: DEFAULT_SRC_DIR)
		precompiler.invokeMethod('workingDirectory_$eq', workingDirectory ?: DEFAULT_WORKDIR)
		precompiler.invokeMethod('targetDirectory_$eq', targetDirectory ?: DEFAULT_CLASSES)
		precompiler.invokeMethod('templates_$eq', templates?.toArray())
		precompiler.invokeMethod('contextClass_$eq', contextClass)
		precompiler.invokeMethod('bootClassName_$eq', bootClassName)
		precompiler.invokeMethod("execute", null);
	}

	private GroovyObject loadPrecompiler(URL[] classpathURLs) {
		def precompilerClassName = 'org.fusesource.scalate.support.Precompiler'

		ClassLoader parent = Thread.currentThread().getContextClassLoader()
		def precompiler = new URLClassLoader(classpathURLs, parent).loadClass(precompilerClassName).newInstance()
		
		return new Proxy().wrap(precompiler)
	}

	private URL[] convertURL(FileCollection files) {
		def paths = files*.path
		println "$paths"
		
		return files.collect {it.toURL()}
	}
}
