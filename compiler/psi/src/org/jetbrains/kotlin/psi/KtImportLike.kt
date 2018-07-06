/*
 * Copyright 2010-2018 JetBrains s.r.o. Use of this source code is governed by the Apache 2.0 license
 * that can be found in the license/LICENSE.txt file.
 */

package org.jetbrains.kotlin.psi

import org.jetbrains.kotlin.name.FqName

interface KtImportLike {
    val isAllUnder: Boolean
    val containingFile: KtFile?
    val importContent: ImportContent?
    val importedFqName: FqName?
    val aliasName: String?

    val importedName: String?
        get() {
            if (isAllUnder) return null
            aliasName?.let { return it }
            val importContent = importContent
            return when (importContent) {
                is KtImportLike.ImportContent.ExpressionBased -> KtPsiUtil.getLastReference(importContent.expression)?.getReferencedName()
                is KtImportLike.ImportContent.FqNameBased -> importContent.fqName.pathSegments().lastOrNull()?.asString()
                null -> null
            }
        }

    sealed class ImportContent {
        class ExpressionBased(val expression: KtExpression) : ImportContent()
        class FqNameBased(val fqName: FqName) : ImportContent()
    }
}
