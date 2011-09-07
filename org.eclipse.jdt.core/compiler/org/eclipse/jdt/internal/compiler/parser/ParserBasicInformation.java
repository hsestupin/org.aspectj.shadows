/*******************************************************************************
 * Copyright (c) 2000, 2007 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.jdt.internal.compiler.parser;

/*An interface that contains static declarations for some basic information
 about the parser such as the number of rules in the grammar, the starting state, etc...*/
public interface ParserBasicInformation {
	 public final static int

     ERROR_SYMBOL      = 117,
     MAX_NAME_LENGTH   = 41,
     NUM_STATES        = 1136,

     NT_OFFSET         = 117,
     SCOPE_UBOUND      = 185,
     SCOPE_SIZE        = 186,
     LA_STATE_OFFSET   = 15467,
     MAX_LA            = 1,
     NUM_RULES         = 889,
     NUM_TERMINALS     = 117,
     NUM_NON_TERMINALS = 372,
     NUM_SYMBOLS       = 489,
     START_STATE       = 930,
     EOFT_SYMBOL       = 74,
     EOLT_SYMBOL       = 74,
     ACCEPT_ACTION     = 15466,
     ERROR_ACTION      = 15467;
}
