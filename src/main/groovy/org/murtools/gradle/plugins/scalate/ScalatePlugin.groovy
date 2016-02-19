package org.murtools.gradle.plugins.scalate

import groovy.util.logging.Slf4j
import org.gradle.api.Plugin
import org.gradle.api.Project

@Slf4j
class ScalatePlugin implements Plugin<Project> {

	static final String SCALATE_PRECOMPILE = 'scalatePreCompile'
	
	@Override
	public void apply(Project project) {
		
		PrecompileTask precompileTask = project.task(SCALATE_PRECOMPILE, type: PrecompileTask)
		precompileTask.description = 'Precompile the scalate templates'
		precompileTask.group = 'Scalate'
		
		project.afterEvaluate {
			precompileTask.dependsOn project.compileScala
			project.classes.dependsOn precompileTask
		}
	}

}