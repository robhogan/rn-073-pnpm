package io.github.robhogan.noderesolver

import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.initialization.Settings

class NodeResolverPlugin implements Plugin<Object> {
    @Override
    void apply(Object target) {
        if (target instanceof Settings || target instanceof Project) {
            target.ext.resolveNodePackage = { String packageName, File startDir = null ->
                NodeResolver.resolvePackage(packageName, startDir ?: target.rootDir)
            }
        } else {
            throw new IllegalArgumentException("Unsupported target type, expected Settings or Project")
        }
    }
}
