package io.github.robhogan.noderesolver

import java.io.File
import org.gradle.api.GradleException

class NodeResolver {
    static File resolvePackage(String packageName, File startDir) {
        File candidate = new File(startDir, "node_modules/$packageName")
        if (candidate.exists()) return candidate.canonicalFile

        File parentDir = startDir.parentFile
        if (parentDir == null || startDir.canonicalPath == parentDir.canonicalPath) {
            throw new GradleException(
                "Failed to find the package '$packageName'. Ensure you have installed node_modules.")
        }
        return resolvePackage(packageName, parentDir)
    }
}