/*
 * Copyright 2010-2018 JetBrains s.r.o. Use of this source code is governed by the Apache 2.0 license
 * that can be found in the license/LICENSE.txt file.
 */

package org.jetbrains.kotlin.fir.declarations

import org.jetbrains.kotlin.descriptors.Modality
import org.jetbrains.kotlin.fir.FirDescriptorOwner
import org.jetbrains.kotlin.fir.visitors.FirVisitor

interface FirResolvedTypeAlias : FirTypeAlias, FirDescriptorOwner<FirResolvedTypeAlias> {
    override val modality: Modality

    override fun <R, D> accept(visitor: FirVisitor<R, D>, data: D): R =
        visitor.visitResolvedTypeAlias(this, data)
}