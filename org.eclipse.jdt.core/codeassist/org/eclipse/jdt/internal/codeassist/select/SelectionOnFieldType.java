/*******************************************************************************
 * Copyright (c) 2000, 2001, 2002 International Business Machines Corp. and others.
 * All rights reserved. This program and the accompanying materials 
 * are made available under the terms of the Common Public License v0.5 
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/cpl-v05.html
 * 
 * Contributors:
 *     IBM Corporation - initial API and implementation
 ******************************************************************************/
package org.eclipse.jdt.internal.codeassist.select;

import org.eclipse.jdt.internal.compiler.ast.FieldDeclaration;
import org.eclipse.jdt.internal.compiler.ast.TypeReference;

public class SelectionOnFieldType extends FieldDeclaration {
	public SelectionOnFieldType(TypeReference type) {
		super();
		this.sourceStart = type.sourceStart;
		this.sourceEnd = type.sourceEnd;
		this.type = type;
		this.name = NoChar;
	}
	public String toString(int tab) {
		return type.toString(tab);
	}
}
