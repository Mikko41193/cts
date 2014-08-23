/*
 * Copyright (C) 2008 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package dot.junit.opcodes.iput_object;

import dot.junit.DxTestCase;
import dot.junit.DxUtil;
import dot.junit.opcodes.iput_object.d.T_iput_object_1;
import dot.junit.opcodes.iput_object.d.T_iput_object_10;
import dot.junit.opcodes.iput_object.d.T_iput_object_11;
import dot.junit.opcodes.iput_object.d.T_iput_object_12;
import dot.junit.opcodes.iput_object.d.T_iput_object_13;
import dot.junit.opcodes.iput_object.d.T_iput_object_14;
import dot.junit.opcodes.iput_object.d.T_iput_object_15;
import dot.junit.opcodes.iput_object.d.T_iput_object_17;
import dot.junit.opcodes.iput_object.d.T_iput_object_7;
import dot.junit.opcodes.iput_object.d.T_iput_object_8;
import dot.junit.opcodes.iput_object.d.T_iput_object_9;

public class Test_iput_object extends DxTestCase {
    /**
     * @title put reference into field
     */
    public void testN1() {
        T_iput_object_1 t = new T_iput_object_1();
        assertEquals(null, t.st_i1);
        t.run();
        assertEquals(t, t.st_i1);
    }


    /**
     * @title modification of final field
     */
    public void testN2() {
        T_iput_object_12 t = new T_iput_object_12();
        assertEquals(null, t.st_i1);
        t.run();
        assertEquals(t, t.st_i1);
    }

    /**
     * @title modification of protected field from subclass
     */
    public void testN4() {
        //@uses dot.junit.opcodes.iput_object.d.T_iput_object_1
        //@uses dot.junit.opcodes.iput_object.d.T_iput_object_14
        T_iput_object_14 t = new T_iput_object_14();
        assertEquals(null, t.getProtectedField());
        t.run();
        assertEquals(t, t.getProtectedField());
    }

    /**
     * @title expected NullPointerException
     */
    public void testE2() {
        loadAndRun("dot.junit.opcodes.iput_object.d.T_iput_object_13", NullPointerException.class);
    }


    /**
     * @constraint A11
     * @title constant pool index
     */
    public void testVFE1() {
        load("dot.junit.opcodes.iput_object.d.T_iput_object_3", VerifyError.class);
    }

    /**
     *
     * @constraint A23
     * @title number of registers
     */
    public void testVFE2() {
        load("dot.junit.opcodes.iput_object.d.T_iput_object_4", VerifyError.class);
    }


    /**
     *
     * @constraint B14
     * @title put object into long field - only field with same name but
     * different type exists
     */
    public void testVFE5() {
        loadAndRun("dot.junit.opcodes.iput_object.d.T_iput_object_17", NoSuchFieldError.class);
    }


    /**
     *
     * @constraint B14
     * @title type of field doesn't match opcode - attempt to modify double
     * field with single-width register
     */
    public void testVFE7() {
        load("dot.junit.opcodes.iput_object.d.T_iput_object_18", VerifyError.class);
    }

    /**
     *
     * @constraint A11
     * @title Attempt to set non-static field.
     */
    public void testVFE8() {
        loadAndRun("dot.junit.opcodes.iput_object.d.T_iput_object_7",
                   IncompatibleClassChangeError.class);
    }

    /**
     * @constraint B12
     * @title Attempt to modify inaccessible protected field.
     */
    public void testVFE9() {
        //@uses dot.junit.opcodes.iput_object.TestStubs
        loadAndRun("dot.junit.opcodes.iput_object.d.T_iput_object_8", IllegalAccessError.class);
    }

    /**
     * @constraint n/a
     * @title Attempt to modify field of undefined class.
     */
    public void testVFE10() {
        loadAndRun("dot.junit.opcodes.iput_object.d.T_iput_object_9", NoClassDefFoundError.class);
    }

    /**
     * @constraint n/a
     * @title Attempt to modify undefined field.
     */
    public void testVFE11() {
        loadAndRun("dot.junit.opcodes.iput_object.d.T_iput_object_10", NoSuchFieldError.class);
    }



    /**
     * @constraint n/a
     * @title Attempt to modify superclass' private field from subclass.
     */
    public void testVFE12() {
        //@uses dot.junit.opcodes.iput_object.d.T_iput_object_1
        loadAndRun("dot.junit.opcodes.iput_object.d.T_iput_object_15", IllegalAccessError.class);
    }


    /**
     * @constraint B1
     * @title iput-object shall not work for wide numbers
     */
    public void testVFE13() {
        load("dot.junit.opcodes.iput_object.d.T_iput_object_2", VerifyError.class);
    }

    /**
     *
     * @constraint B13
     * @title assignment incompatible references
     */
    public void testVFE14() {
        load("dot.junit.opcodes.iput_object.d.T_iput_object_20", VerifyError.class);
    }

    /**
     *
     * @constraint B1
     * @title iput-object shall not work for char fields
     */
    public void testVFE15() {
        load("dot.junit.opcodes.iput_object.d.T_iput_object_21", VerifyError.class);
    }

    /**
     *
     * @constraint B1
     * @title iput-object shall not work for int fields
     */
    public void testVFE16() {
        load("dot.junit.opcodes.iput_object.d.T_iput_object_22", VerifyError.class);
    }

    /**
     *
     * @constraint B1
     * @title iput-object shall not work for byte fields
     */
    public void testVFE17() {
        load("dot.junit.opcodes.iput_object.d.T_iput_object_23", VerifyError.class);
    }

    /**
     *
     * @constraint B1
     * @title iput-object shall not work for boolean fields
     */
    public void testVFE18() {
        load("dot.junit.opcodes.iput_object.d.T_iput_object_24", VerifyError.class);
    }

    /**
     *
     * @constraint B1
     * @title iput-object shall not work for short fields
     */
    public void testVFE6() {
        load("dot.junit.opcodes.iput_object.d.T_iput_object_6", VerifyError.class);
    }

    /**
     * @constraint B6
     * @title instance fields may only be accessed on already initialized instances.
     */
    public void testVFE30() {
        load("dot.junit.opcodes.iput_object.d.T_iput_object_30", VerifyError.class);
    }

    /**
     * @constraint N/A
     * @title instance fields may only be accessed on reference registers.
     */
    public void testVFE31() {
        load("dot.junit.opcodes.iput_object.d.T_iput_object_31", VerifyError.class);
    }

    /**
     * @constraint n/a
     * @title Modification of final field in other class
     */
    public void testVFE19() {
        //@uses dot.junit.opcodes.iput_object.TestStubs
        loadAndRun("dot.junit.opcodes.iput_object.d.T_iput_object_11", IllegalAccessError.class);
    }
}

