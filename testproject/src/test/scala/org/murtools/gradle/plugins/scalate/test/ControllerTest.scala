package org.murtools.gradle.plugins.scalate.test

import org.junit._
import Assert._

class ControllerTest {
	@Test def render {
		val output = """<p>Hi scalate,</p>
<p>1</p>
<p>2</p>
<p>3</p>
<p>See, I can count!</p>"""

		val controller = new Controller
		val actual = controller.execute(new Person("scalate"))
		assertEquals(actual, output)
	}
}