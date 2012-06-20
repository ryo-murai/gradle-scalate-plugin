package org.murtools

import groovy.util.logging.Slf4j;
import groovy.util.Proxy
import org.gradle.api.DefaultTask
import org.gradle.api.file.FileCollection;
import org.gradle.api.tasks.TaskAction;

@Slf4j
class PrecompileTask extends DefaultTask {
	FileCollection classpath
	File templateSrcDir
	File workingDirectory
	File targetDirectory 
	List<String> templates
	String contextClass
	String bootClassName
	
	@TaskAction
	protected void start() {
		debug()
		
		def precompiler = loadPrecompiler()
		precompiler.invokeMethod('sources_$eq', templateSrcDir)
		precompiler.invokeMethod('workingDirectory_$eq', workingDirectory)
		precompiler.invokeMethod('targetDirectory_$eq', targetDirectory)
		precompiler.invokeMethod('templates_$eq', templates?.toArray())
		precompiler.invokeMethod('contextClass_$eq', contextClass)
		precompiler.invokeMethod('bootClassName_$eq', bootClassName)
		precompiler.invokeMethod("execute", null);
	}
	
	private GroovyObject loadPrecompiler() {
		def precompilerClassName = 'org.fusesource.scalate.support.Precompiler'

		ClassLoader parent = Thread.currentThread().getContextClassLoader()
		def precompiler = new URLClassLoader(convertURL(classpath), parent).loadClass(precompilerClassName).newInstance()
		
		return new Proxy().wrap(precompiler)
	}
	
	private URL[] convertURL(FileCollection files) {
		return files.collect {new URL(it.absolutePath)}
	}

	private void debug() {
		System.out.println "$classpath is a " + classpath.class + ", contains " + classpath.files.size()
		classpath.each  { System.out.println it }
	}
	
}
