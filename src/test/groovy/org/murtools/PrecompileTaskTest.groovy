package org.murtools

import org.codehaus.groovy.runtime.wrappers.GroovyObjectWrapper
import org.gradle.api.Project
import org.junit.Test
import org.fusesource.scalate.support.Precompiler
import org.gradle.testfixtures.ProjectBuilder
import static org.junit.Assert.assertTrue

class PrecompileTaskTest {
	@Test
	public void precompileTest() {
		System.out.println 'hoge'
		Project project = ProjectBuilder.builder().build()
		def task = project.task('scalatePrecompile', type: PrecompileTask)
		assertTrue(task instanceof PrecompileTask)

		task.templateSrcDir = new File('src/test/webapp/scalate_tmplates')
		task.workingDirectory = new File('build/test-output/scalate/work')
		task.targetDirectory = new File('build/test-output/scalate/target')
		task.execute()
	}
}