# Gradle Scalate plugin

This plugin provides tasks for [Scalate](http://scalate.fusesource.org/)
in Gradle build script. Currently, task for `precompile` of templates is supported.

*Note:* [Scalate](http://scalate.fusesource.org/) already provides its [official plugins to Precompile Templates](http://scalate.fusesource.org/documentation/user-guide.html#precompiling_templates) for Maven and SBT. Use of the official plugin in Maven or SBT is strongly recommended. Use this plugin ** only when ** you somehow have to precompile your scalate templates in Gradle build and unable to change it to Maven or SBT.

## Prerequired

* The plugin depends on `'scala'` plugin. Include below in your build script.

    apply plugin: 'scala'
    

* Add necessary dependencies for scala. See [user manual for "gradle scala plugin"](http://gradle.org/docs/current/userguide/scala_plugin.html#N12952).

* Add scalate-core.jar in your project dependencies with `:compile` configuration.

        dependencies {
            compile 'org.fusesource.scalate:scalate-core:1.5.3'
        }


## Usage

To use the plugin, include in your build script:

    apply plugin: 'scalate'

The plugin JAR is needed in the classpath of your build script. 
There is no public repository available for the plugin. Instead, it is shared in a Dropbox link. Below script snippet is an example on how to retrieve the plugin jar.

    buildscript {
        repositories {
            ivy {
                url 'http://dl.dropbox.com/u/1644238/mvnrepository/'
            }
        }

        dependencies {
            classpath 'murtools:gradle-scalate-plugin:0.0.1'
        }
    }




## Tasks

The plugin defines the following tasks:

* `scalatePreCompile`: precompile your templates of ssp, scaml etc. Below properties are defined for this task.
 * `templateSrcDir`: (File) root directory where your scalate templates located. Default is `src/main/webapp`.
 * `workingDirectory`: (File) directory where generated .scala files will be located.
 * `targetDirectory`: (File) directrory where compiled .class files will be located. Default is `build/classes/main`
 * `templates`: (File) path of additional template file aside from the `templateSrcDir`
 * `contextClass`: (String) class name of [RenderContext](http://scalate.fusesource.org/maven/1.5.3/scalate-core/scaladocs/org/fusesource/scalate/RenderContext.html) to precompile. Default is [ServletRenderContext](http://scalate.fusesource.org/maven/1.5.3/scalate-core/scaladocs/org/fusesource/scalate/servlet/ServletRenderContext.html)
 * `bootClassName`: (String) boot class name


## Todo
* 
