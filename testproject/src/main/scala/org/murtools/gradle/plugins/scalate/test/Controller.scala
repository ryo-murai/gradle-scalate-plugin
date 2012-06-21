package org.murtools.gradle.plugins.scalate.test

import org.fusesource.scalate._

class Controller {
	def execute(p : Person): String = {
		var te = new TemplateEngine
		te.layout("user.ssp", Map("user" -> p))
	}
}