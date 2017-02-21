/*
 * Copyright (C) 2016 The Android Open Source Project
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

// Don't edit this file!  It is auto-generated by frameworks/rs/api/generate.sh.

package android.renderscript.cts;

import android.renderscript.Allocation;
import android.renderscript.RSRuntimeException;
import android.renderscript.Element;
import android.renderscript.cts.Target;

import java.util.Arrays;

public class TestPown extends RSBaseCompute {

    private ScriptC_TestPown script;
    private ScriptC_TestPownRelaxed scriptRelaxed;

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        script = new ScriptC_TestPown(mRS);
        scriptRelaxed = new ScriptC_TestPownRelaxed(mRS);
    }

    public class ArgumentsFloatIntFloat {
        public float inBase;
        public int inExponent;
        public Target.Floaty out;
    }

    private void checkPownFloatIntFloat() {
        Allocation inBase = createRandomAllocation(mRS, Element.DataType.FLOAT_32, 1, 0xe020952d622f0405l, false);
        Allocation inExponent = createRandomAllocation(mRS, Element.DataType.SIGNED_32, 1, 0x9c3888e9096b9f1bl, false);
        try {
            Allocation out = Allocation.createSized(mRS, getElement(mRS, Element.DataType.FLOAT_32, 1), INPUTSIZE);
            script.set_gAllocInExponent(inExponent);
            script.forEach_testPownFloatIntFloat(inBase, out);
            verifyResultsPownFloatIntFloat(inBase, inExponent, out, false);
        } catch (Exception e) {
            throw new RSRuntimeException("RenderScript. Can't invoke forEach_testPownFloatIntFloat: " + e.toString());
        }
        try {
            Allocation out = Allocation.createSized(mRS, getElement(mRS, Element.DataType.FLOAT_32, 1), INPUTSIZE);
            scriptRelaxed.set_gAllocInExponent(inExponent);
            scriptRelaxed.forEach_testPownFloatIntFloat(inBase, out);
            verifyResultsPownFloatIntFloat(inBase, inExponent, out, true);
        } catch (Exception e) {
            throw new RSRuntimeException("RenderScript. Can't invoke forEach_testPownFloatIntFloat: " + e.toString());
        }
    }

    private void verifyResultsPownFloatIntFloat(Allocation inBase, Allocation inExponent, Allocation out, boolean relaxed) {
        float[] arrayInBase = new float[INPUTSIZE * 1];
        Arrays.fill(arrayInBase, (float) 42);
        inBase.copyTo(arrayInBase);
        int[] arrayInExponent = new int[INPUTSIZE * 1];
        Arrays.fill(arrayInExponent, (int) 42);
        inExponent.copyTo(arrayInExponent);
        float[] arrayOut = new float[INPUTSIZE * 1];
        Arrays.fill(arrayOut, (float) 42);
        out.copyTo(arrayOut);
        StringBuilder message = new StringBuilder();
        boolean errorFound = false;
        for (int i = 0; i < INPUTSIZE; i++) {
            for (int j = 0; j < 1 ; j++) {
                // Extract the inputs.
                ArgumentsFloatIntFloat args = new ArgumentsFloatIntFloat();
                args.inBase = arrayInBase[i];
                args.inExponent = arrayInExponent[i];
                // Figure out what the outputs should have been.
                Target target = new Target(Target.FunctionType.NORMAL, Target.ReturnType.FLOAT, relaxed);
                CoreMathVerifier.computePown(args, target);
                // Validate the outputs.
                boolean valid = true;
                if (!args.out.couldBe(arrayOut[i * 1 + j])) {
                    valid = false;
                }
                if (!valid) {
                    if (!errorFound) {
                        errorFound = true;
                        message.append("Input inBase: ");
                        appendVariableToMessage(message, args.inBase);
                        message.append("\n");
                        message.append("Input inExponent: ");
                        appendVariableToMessage(message, args.inExponent);
                        message.append("\n");
                        message.append("Expected output out: ");
                        appendVariableToMessage(message, args.out);
                        message.append("\n");
                        message.append("Actual   output out: ");
                        appendVariableToMessage(message, arrayOut[i * 1 + j]);
                        if (!args.out.couldBe(arrayOut[i * 1 + j])) {
                            message.append(" FAIL");
                        }
                        message.append("\n");
                        message.append("Errors at");
                    }
                    message.append(" [");
                    message.append(Integer.toString(i));
                    message.append(", ");
                    message.append(Integer.toString(j));
                    message.append("]");
                }
            }
        }
        assertFalse("Incorrect output for checkPownFloatIntFloat" +
                (relaxed ? "_relaxed" : "") + ":\n" + message.toString(), errorFound);
    }

    private void checkPownFloat2Int2Float2() {
        Allocation inBase = createRandomAllocation(mRS, Element.DataType.FLOAT_32, 2, 0x7571c02be438467l, false);
        Allocation inExponent = createRandomAllocation(mRS, Element.DataType.SIGNED_32, 2, 0xe6177b3249076ddl, false);
        try {
            Allocation out = Allocation.createSized(mRS, getElement(mRS, Element.DataType.FLOAT_32, 2), INPUTSIZE);
            script.set_gAllocInExponent(inExponent);
            script.forEach_testPownFloat2Int2Float2(inBase, out);
            verifyResultsPownFloat2Int2Float2(inBase, inExponent, out, false);
        } catch (Exception e) {
            throw new RSRuntimeException("RenderScript. Can't invoke forEach_testPownFloat2Int2Float2: " + e.toString());
        }
        try {
            Allocation out = Allocation.createSized(mRS, getElement(mRS, Element.DataType.FLOAT_32, 2), INPUTSIZE);
            scriptRelaxed.set_gAllocInExponent(inExponent);
            scriptRelaxed.forEach_testPownFloat2Int2Float2(inBase, out);
            verifyResultsPownFloat2Int2Float2(inBase, inExponent, out, true);
        } catch (Exception e) {
            throw new RSRuntimeException("RenderScript. Can't invoke forEach_testPownFloat2Int2Float2: " + e.toString());
        }
    }

    private void verifyResultsPownFloat2Int2Float2(Allocation inBase, Allocation inExponent, Allocation out, boolean relaxed) {
        float[] arrayInBase = new float[INPUTSIZE * 2];
        Arrays.fill(arrayInBase, (float) 42);
        inBase.copyTo(arrayInBase);
        int[] arrayInExponent = new int[INPUTSIZE * 2];
        Arrays.fill(arrayInExponent, (int) 42);
        inExponent.copyTo(arrayInExponent);
        float[] arrayOut = new float[INPUTSIZE * 2];
        Arrays.fill(arrayOut, (float) 42);
        out.copyTo(arrayOut);
        StringBuilder message = new StringBuilder();
        boolean errorFound = false;
        for (int i = 0; i < INPUTSIZE; i++) {
            for (int j = 0; j < 2 ; j++) {
                // Extract the inputs.
                ArgumentsFloatIntFloat args = new ArgumentsFloatIntFloat();
                args.inBase = arrayInBase[i * 2 + j];
                args.inExponent = arrayInExponent[i * 2 + j];
                // Figure out what the outputs should have been.
                Target target = new Target(Target.FunctionType.NORMAL, Target.ReturnType.FLOAT, relaxed);
                CoreMathVerifier.computePown(args, target);
                // Validate the outputs.
                boolean valid = true;
                if (!args.out.couldBe(arrayOut[i * 2 + j])) {
                    valid = false;
                }
                if (!valid) {
                    if (!errorFound) {
                        errorFound = true;
                        message.append("Input inBase: ");
                        appendVariableToMessage(message, args.inBase);
                        message.append("\n");
                        message.append("Input inExponent: ");
                        appendVariableToMessage(message, args.inExponent);
                        message.append("\n");
                        message.append("Expected output out: ");
                        appendVariableToMessage(message, args.out);
                        message.append("\n");
                        message.append("Actual   output out: ");
                        appendVariableToMessage(message, arrayOut[i * 2 + j]);
                        if (!args.out.couldBe(arrayOut[i * 2 + j])) {
                            message.append(" FAIL");
                        }
                        message.append("\n");
                        message.append("Errors at");
                    }
                    message.append(" [");
                    message.append(Integer.toString(i));
                    message.append(", ");
                    message.append(Integer.toString(j));
                    message.append("]");
                }
            }
        }
        assertFalse("Incorrect output for checkPownFloat2Int2Float2" +
                (relaxed ? "_relaxed" : "") + ":\n" + message.toString(), errorFound);
    }

    private void checkPownFloat3Int3Float3() {
        Allocation inBase = createRandomAllocation(mRS, Element.DataType.FLOAT_32, 3, 0x5e88c45be35ff8a2l, false);
        Allocation inExponent = createRandomAllocation(mRS, Element.DataType.SIGNED_32, 3, 0xaf710734144a81a8l, false);
        try {
            Allocation out = Allocation.createSized(mRS, getElement(mRS, Element.DataType.FLOAT_32, 3), INPUTSIZE);
            script.set_gAllocInExponent(inExponent);
            script.forEach_testPownFloat3Int3Float3(inBase, out);
            verifyResultsPownFloat3Int3Float3(inBase, inExponent, out, false);
        } catch (Exception e) {
            throw new RSRuntimeException("RenderScript. Can't invoke forEach_testPownFloat3Int3Float3: " + e.toString());
        }
        try {
            Allocation out = Allocation.createSized(mRS, getElement(mRS, Element.DataType.FLOAT_32, 3), INPUTSIZE);
            scriptRelaxed.set_gAllocInExponent(inExponent);
            scriptRelaxed.forEach_testPownFloat3Int3Float3(inBase, out);
            verifyResultsPownFloat3Int3Float3(inBase, inExponent, out, true);
        } catch (Exception e) {
            throw new RSRuntimeException("RenderScript. Can't invoke forEach_testPownFloat3Int3Float3: " + e.toString());
        }
    }

    private void verifyResultsPownFloat3Int3Float3(Allocation inBase, Allocation inExponent, Allocation out, boolean relaxed) {
        float[] arrayInBase = new float[INPUTSIZE * 4];
        Arrays.fill(arrayInBase, (float) 42);
        inBase.copyTo(arrayInBase);
        int[] arrayInExponent = new int[INPUTSIZE * 4];
        Arrays.fill(arrayInExponent, (int) 42);
        inExponent.copyTo(arrayInExponent);
        float[] arrayOut = new float[INPUTSIZE * 4];
        Arrays.fill(arrayOut, (float) 42);
        out.copyTo(arrayOut);
        StringBuilder message = new StringBuilder();
        boolean errorFound = false;
        for (int i = 0; i < INPUTSIZE; i++) {
            for (int j = 0; j < 3 ; j++) {
                // Extract the inputs.
                ArgumentsFloatIntFloat args = new ArgumentsFloatIntFloat();
                args.inBase = arrayInBase[i * 4 + j];
                args.inExponent = arrayInExponent[i * 4 + j];
                // Figure out what the outputs should have been.
                Target target = new Target(Target.FunctionType.NORMAL, Target.ReturnType.FLOAT, relaxed);
                CoreMathVerifier.computePown(args, target);
                // Validate the outputs.
                boolean valid = true;
                if (!args.out.couldBe(arrayOut[i * 4 + j])) {
                    valid = false;
                }
                if (!valid) {
                    if (!errorFound) {
                        errorFound = true;
                        message.append("Input inBase: ");
                        appendVariableToMessage(message, args.inBase);
                        message.append("\n");
                        message.append("Input inExponent: ");
                        appendVariableToMessage(message, args.inExponent);
                        message.append("\n");
                        message.append("Expected output out: ");
                        appendVariableToMessage(message, args.out);
                        message.append("\n");
                        message.append("Actual   output out: ");
                        appendVariableToMessage(message, arrayOut[i * 4 + j]);
                        if (!args.out.couldBe(arrayOut[i * 4 + j])) {
                            message.append(" FAIL");
                        }
                        message.append("\n");
                        message.append("Errors at");
                    }
                    message.append(" [");
                    message.append(Integer.toString(i));
                    message.append(", ");
                    message.append(Integer.toString(j));
                    message.append("]");
                }
            }
        }
        assertFalse("Incorrect output for checkPownFloat3Int3Float3" +
                (relaxed ? "_relaxed" : "") + ":\n" + message.toString(), errorFound);
    }

    private void checkPownFloat4Int4Float4() {
        Allocation inBase = createRandomAllocation(mRS, Element.DataType.FLOAT_32, 4, 0xb5ba6cb5087c6cddl, false);
        Allocation inExponent = createRandomAllocation(mRS, Element.DataType.SIGNED_32, 4, 0x508096b504048c73l, false);
        try {
            Allocation out = Allocation.createSized(mRS, getElement(mRS, Element.DataType.FLOAT_32, 4), INPUTSIZE);
            script.set_gAllocInExponent(inExponent);
            script.forEach_testPownFloat4Int4Float4(inBase, out);
            verifyResultsPownFloat4Int4Float4(inBase, inExponent, out, false);
        } catch (Exception e) {
            throw new RSRuntimeException("RenderScript. Can't invoke forEach_testPownFloat4Int4Float4: " + e.toString());
        }
        try {
            Allocation out = Allocation.createSized(mRS, getElement(mRS, Element.DataType.FLOAT_32, 4), INPUTSIZE);
            scriptRelaxed.set_gAllocInExponent(inExponent);
            scriptRelaxed.forEach_testPownFloat4Int4Float4(inBase, out);
            verifyResultsPownFloat4Int4Float4(inBase, inExponent, out, true);
        } catch (Exception e) {
            throw new RSRuntimeException("RenderScript. Can't invoke forEach_testPownFloat4Int4Float4: " + e.toString());
        }
    }

    private void verifyResultsPownFloat4Int4Float4(Allocation inBase, Allocation inExponent, Allocation out, boolean relaxed) {
        float[] arrayInBase = new float[INPUTSIZE * 4];
        Arrays.fill(arrayInBase, (float) 42);
        inBase.copyTo(arrayInBase);
        int[] arrayInExponent = new int[INPUTSIZE * 4];
        Arrays.fill(arrayInExponent, (int) 42);
        inExponent.copyTo(arrayInExponent);
        float[] arrayOut = new float[INPUTSIZE * 4];
        Arrays.fill(arrayOut, (float) 42);
        out.copyTo(arrayOut);
        StringBuilder message = new StringBuilder();
        boolean errorFound = false;
        for (int i = 0; i < INPUTSIZE; i++) {
            for (int j = 0; j < 4 ; j++) {
                // Extract the inputs.
                ArgumentsFloatIntFloat args = new ArgumentsFloatIntFloat();
                args.inBase = arrayInBase[i * 4 + j];
                args.inExponent = arrayInExponent[i * 4 + j];
                // Figure out what the outputs should have been.
                Target target = new Target(Target.FunctionType.NORMAL, Target.ReturnType.FLOAT, relaxed);
                CoreMathVerifier.computePown(args, target);
                // Validate the outputs.
                boolean valid = true;
                if (!args.out.couldBe(arrayOut[i * 4 + j])) {
                    valid = false;
                }
                if (!valid) {
                    if (!errorFound) {
                        errorFound = true;
                        message.append("Input inBase: ");
                        appendVariableToMessage(message, args.inBase);
                        message.append("\n");
                        message.append("Input inExponent: ");
                        appendVariableToMessage(message, args.inExponent);
                        message.append("\n");
                        message.append("Expected output out: ");
                        appendVariableToMessage(message, args.out);
                        message.append("\n");
                        message.append("Actual   output out: ");
                        appendVariableToMessage(message, arrayOut[i * 4 + j]);
                        if (!args.out.couldBe(arrayOut[i * 4 + j])) {
                            message.append(" FAIL");
                        }
                        message.append("\n");
                        message.append("Errors at");
                    }
                    message.append(" [");
                    message.append(Integer.toString(i));
                    message.append(", ");
                    message.append(Integer.toString(j));
                    message.append("]");
                }
            }
        }
        assertFalse("Incorrect output for checkPownFloat4Int4Float4" +
                (relaxed ? "_relaxed" : "") + ":\n" + message.toString(), errorFound);
    }

    public class ArgumentsHalfIntHalf {
        public short inBase;
        public double inBaseDouble;
        public int inExponent;
        public Target.Floaty out;
    }

    private void checkPownHalfIntHalf() {
        Allocation inBase = createRandomAllocation(mRS, Element.DataType.FLOAT_16, 1, 0x89409624858a42bbl, false);
        Allocation inExponent = createRandomAllocation(mRS, Element.DataType.SIGNED_32, 1, 0xfdb4ee1e30c3d6f1l, false);
        try {
            Allocation out = Allocation.createSized(mRS, getElement(mRS, Element.DataType.FLOAT_16, 1), INPUTSIZE);
            script.set_gAllocInExponent(inExponent);
            script.forEach_testPownHalfIntHalf(inBase, out);
            verifyResultsPownHalfIntHalf(inBase, inExponent, out, false);
        } catch (Exception e) {
            throw new RSRuntimeException("RenderScript. Can't invoke forEach_testPownHalfIntHalf: " + e.toString());
        }
        try {
            Allocation out = Allocation.createSized(mRS, getElement(mRS, Element.DataType.FLOAT_16, 1), INPUTSIZE);
            scriptRelaxed.set_gAllocInExponent(inExponent);
            scriptRelaxed.forEach_testPownHalfIntHalf(inBase, out);
            verifyResultsPownHalfIntHalf(inBase, inExponent, out, true);
        } catch (Exception e) {
            throw new RSRuntimeException("RenderScript. Can't invoke forEach_testPownHalfIntHalf: " + e.toString());
        }
    }

    private void verifyResultsPownHalfIntHalf(Allocation inBase, Allocation inExponent, Allocation out, boolean relaxed) {
        short[] arrayInBase = new short[INPUTSIZE * 1];
        Arrays.fill(arrayInBase, (short) 42);
        inBase.copyTo(arrayInBase);
        int[] arrayInExponent = new int[INPUTSIZE * 1];
        Arrays.fill(arrayInExponent, (int) 42);
        inExponent.copyTo(arrayInExponent);
        short[] arrayOut = new short[INPUTSIZE * 1];
        Arrays.fill(arrayOut, (short) 42);
        out.copyTo(arrayOut);
        StringBuilder message = new StringBuilder();
        boolean errorFound = false;
        for (int i = 0; i < INPUTSIZE; i++) {
            for (int j = 0; j < 1 ; j++) {
                // Extract the inputs.
                ArgumentsHalfIntHalf args = new ArgumentsHalfIntHalf();
                args.inBase = arrayInBase[i];
                args.inBaseDouble = Float16Utils.convertFloat16ToDouble(args.inBase);
                args.inExponent = arrayInExponent[i];
                // Figure out what the outputs should have been.
                Target target = new Target(Target.FunctionType.NORMAL, Target.ReturnType.HALF, relaxed);
                CoreMathVerifier.computePown(args, target);
                // Validate the outputs.
                boolean valid = true;
                if (!args.out.couldBe(Float16Utils.convertFloat16ToDouble(arrayOut[i * 1 + j]))) {
                    valid = false;
                }
                if (!valid) {
                    if (!errorFound) {
                        errorFound = true;
                        message.append("Input inBase: ");
                        appendVariableToMessage(message, args.inBase);
                        message.append("\n");
                        message.append("Input inExponent: ");
                        appendVariableToMessage(message, args.inExponent);
                        message.append("\n");
                        message.append("Expected output out: ");
                        appendVariableToMessage(message, args.out);
                        message.append("\n");
                        message.append("Actual   output out: ");
                        appendVariableToMessage(message, arrayOut[i * 1 + j]);
                        message.append("\n");
                        message.append("Actual   output out (in double): ");
                        appendVariableToMessage(message, Float16Utils.convertFloat16ToDouble(arrayOut[i * 1 + j]));
                        if (!args.out.couldBe(Float16Utils.convertFloat16ToDouble(arrayOut[i * 1 + j]))) {
                            message.append(" FAIL");
                        }
                        message.append("\n");
                        message.append("Errors at");
                    }
                    message.append(" [");
                    message.append(Integer.toString(i));
                    message.append(", ");
                    message.append(Integer.toString(j));
                    message.append("]");
                }
            }
        }
        assertFalse("Incorrect output for checkPownHalfIntHalf" +
                (relaxed ? "_relaxed" : "") + ":\n" + message.toString(), errorFound);
    }

    private void checkPownHalf2Int2Half2() {
        Allocation inBase = createRandomAllocation(mRS, Element.DataType.FLOAT_16, 2, 0x218972b229ccb2d5l, false);
        Allocation inExponent = createRandomAllocation(mRS, Element.DataType.SIGNED_32, 2, 0x90a067e6e5f9cebl, false);
        try {
            Allocation out = Allocation.createSized(mRS, getElement(mRS, Element.DataType.FLOAT_16, 2), INPUTSIZE);
            script.set_gAllocInExponent(inExponent);
            script.forEach_testPownHalf2Int2Half2(inBase, out);
            verifyResultsPownHalf2Int2Half2(inBase, inExponent, out, false);
        } catch (Exception e) {
            throw new RSRuntimeException("RenderScript. Can't invoke forEach_testPownHalf2Int2Half2: " + e.toString());
        }
        try {
            Allocation out = Allocation.createSized(mRS, getElement(mRS, Element.DataType.FLOAT_16, 2), INPUTSIZE);
            scriptRelaxed.set_gAllocInExponent(inExponent);
            scriptRelaxed.forEach_testPownHalf2Int2Half2(inBase, out);
            verifyResultsPownHalf2Int2Half2(inBase, inExponent, out, true);
        } catch (Exception e) {
            throw new RSRuntimeException("RenderScript. Can't invoke forEach_testPownHalf2Int2Half2: " + e.toString());
        }
    }

    private void verifyResultsPownHalf2Int2Half2(Allocation inBase, Allocation inExponent, Allocation out, boolean relaxed) {
        short[] arrayInBase = new short[INPUTSIZE * 2];
        Arrays.fill(arrayInBase, (short) 42);
        inBase.copyTo(arrayInBase);
        int[] arrayInExponent = new int[INPUTSIZE * 2];
        Arrays.fill(arrayInExponent, (int) 42);
        inExponent.copyTo(arrayInExponent);
        short[] arrayOut = new short[INPUTSIZE * 2];
        Arrays.fill(arrayOut, (short) 42);
        out.copyTo(arrayOut);
        StringBuilder message = new StringBuilder();
        boolean errorFound = false;
        for (int i = 0; i < INPUTSIZE; i++) {
            for (int j = 0; j < 2 ; j++) {
                // Extract the inputs.
                ArgumentsHalfIntHalf args = new ArgumentsHalfIntHalf();
                args.inBase = arrayInBase[i * 2 + j];
                args.inBaseDouble = Float16Utils.convertFloat16ToDouble(args.inBase);
                args.inExponent = arrayInExponent[i * 2 + j];
                // Figure out what the outputs should have been.
                Target target = new Target(Target.FunctionType.NORMAL, Target.ReturnType.HALF, relaxed);
                CoreMathVerifier.computePown(args, target);
                // Validate the outputs.
                boolean valid = true;
                if (!args.out.couldBe(Float16Utils.convertFloat16ToDouble(arrayOut[i * 2 + j]))) {
                    valid = false;
                }
                if (!valid) {
                    if (!errorFound) {
                        errorFound = true;
                        message.append("Input inBase: ");
                        appendVariableToMessage(message, args.inBase);
                        message.append("\n");
                        message.append("Input inExponent: ");
                        appendVariableToMessage(message, args.inExponent);
                        message.append("\n");
                        message.append("Expected output out: ");
                        appendVariableToMessage(message, args.out);
                        message.append("\n");
                        message.append("Actual   output out: ");
                        appendVariableToMessage(message, arrayOut[i * 2 + j]);
                        message.append("\n");
                        message.append("Actual   output out (in double): ");
                        appendVariableToMessage(message, Float16Utils.convertFloat16ToDouble(arrayOut[i * 2 + j]));
                        if (!args.out.couldBe(Float16Utils.convertFloat16ToDouble(arrayOut[i * 2 + j]))) {
                            message.append(" FAIL");
                        }
                        message.append("\n");
                        message.append("Errors at");
                    }
                    message.append(" [");
                    message.append(Integer.toString(i));
                    message.append(", ");
                    message.append(Integer.toString(j));
                    message.append("]");
                }
            }
        }
        assertFalse("Incorrect output for checkPownHalf2Int2Half2" +
                (relaxed ? "_relaxed" : "") + ":\n" + message.toString(), errorFound);
    }

    private void checkPownHalf3Int3Half3() {
        Allocation inBase = createRandomAllocation(mRS, Element.DataType.FLOAT_16, 3, 0x82d20b742f8523b4l, false);
        Allocation inExponent = createRandomAllocation(mRS, Element.DataType.SIGNED_32, 3, 0x3cb4a30b92b63d1al, false);
        try {
            Allocation out = Allocation.createSized(mRS, getElement(mRS, Element.DataType.FLOAT_16, 3), INPUTSIZE);
            script.set_gAllocInExponent(inExponent);
            script.forEach_testPownHalf3Int3Half3(inBase, out);
            verifyResultsPownHalf3Int3Half3(inBase, inExponent, out, false);
        } catch (Exception e) {
            throw new RSRuntimeException("RenderScript. Can't invoke forEach_testPownHalf3Int3Half3: " + e.toString());
        }
        try {
            Allocation out = Allocation.createSized(mRS, getElement(mRS, Element.DataType.FLOAT_16, 3), INPUTSIZE);
            scriptRelaxed.set_gAllocInExponent(inExponent);
            scriptRelaxed.forEach_testPownHalf3Int3Half3(inBase, out);
            verifyResultsPownHalf3Int3Half3(inBase, inExponent, out, true);
        } catch (Exception e) {
            throw new RSRuntimeException("RenderScript. Can't invoke forEach_testPownHalf3Int3Half3: " + e.toString());
        }
    }

    private void verifyResultsPownHalf3Int3Half3(Allocation inBase, Allocation inExponent, Allocation out, boolean relaxed) {
        short[] arrayInBase = new short[INPUTSIZE * 4];
        Arrays.fill(arrayInBase, (short) 42);
        inBase.copyTo(arrayInBase);
        int[] arrayInExponent = new int[INPUTSIZE * 4];
        Arrays.fill(arrayInExponent, (int) 42);
        inExponent.copyTo(arrayInExponent);
        short[] arrayOut = new short[INPUTSIZE * 4];
        Arrays.fill(arrayOut, (short) 42);
        out.copyTo(arrayOut);
        StringBuilder message = new StringBuilder();
        boolean errorFound = false;
        for (int i = 0; i < INPUTSIZE; i++) {
            for (int j = 0; j < 3 ; j++) {
                // Extract the inputs.
                ArgumentsHalfIntHalf args = new ArgumentsHalfIntHalf();
                args.inBase = arrayInBase[i * 4 + j];
                args.inBaseDouble = Float16Utils.convertFloat16ToDouble(args.inBase);
                args.inExponent = arrayInExponent[i * 4 + j];
                // Figure out what the outputs should have been.
                Target target = new Target(Target.FunctionType.NORMAL, Target.ReturnType.HALF, relaxed);
                CoreMathVerifier.computePown(args, target);
                // Validate the outputs.
                boolean valid = true;
                if (!args.out.couldBe(Float16Utils.convertFloat16ToDouble(arrayOut[i * 4 + j]))) {
                    valid = false;
                }
                if (!valid) {
                    if (!errorFound) {
                        errorFound = true;
                        message.append("Input inBase: ");
                        appendVariableToMessage(message, args.inBase);
                        message.append("\n");
                        message.append("Input inExponent: ");
                        appendVariableToMessage(message, args.inExponent);
                        message.append("\n");
                        message.append("Expected output out: ");
                        appendVariableToMessage(message, args.out);
                        message.append("\n");
                        message.append("Actual   output out: ");
                        appendVariableToMessage(message, arrayOut[i * 4 + j]);
                        message.append("\n");
                        message.append("Actual   output out (in double): ");
                        appendVariableToMessage(message, Float16Utils.convertFloat16ToDouble(arrayOut[i * 4 + j]));
                        if (!args.out.couldBe(Float16Utils.convertFloat16ToDouble(arrayOut[i * 4 + j]))) {
                            message.append(" FAIL");
                        }
                        message.append("\n");
                        message.append("Errors at");
                    }
                    message.append(" [");
                    message.append(Integer.toString(i));
                    message.append(", ");
                    message.append(Integer.toString(j));
                    message.append("]");
                }
            }
        }
        assertFalse("Incorrect output for checkPownHalf3Int3Half3" +
                (relaxed ? "_relaxed" : "") + ":\n" + message.toString(), errorFound);
    }

    private void checkPownHalf4Int4Half4() {
        Allocation inBase = createRandomAllocation(mRS, Element.DataType.FLOAT_16, 4, 0xe41aa436353d9493l, false);
        Allocation inExponent = createRandomAllocation(mRS, Element.DataType.SIGNED_32, 4, 0x705f3f98b70cdd49l, false);
        try {
            Allocation out = Allocation.createSized(mRS, getElement(mRS, Element.DataType.FLOAT_16, 4), INPUTSIZE);
            script.set_gAllocInExponent(inExponent);
            script.forEach_testPownHalf4Int4Half4(inBase, out);
            verifyResultsPownHalf4Int4Half4(inBase, inExponent, out, false);
        } catch (Exception e) {
            throw new RSRuntimeException("RenderScript. Can't invoke forEach_testPownHalf4Int4Half4: " + e.toString());
        }
        try {
            Allocation out = Allocation.createSized(mRS, getElement(mRS, Element.DataType.FLOAT_16, 4), INPUTSIZE);
            scriptRelaxed.set_gAllocInExponent(inExponent);
            scriptRelaxed.forEach_testPownHalf4Int4Half4(inBase, out);
            verifyResultsPownHalf4Int4Half4(inBase, inExponent, out, true);
        } catch (Exception e) {
            throw new RSRuntimeException("RenderScript. Can't invoke forEach_testPownHalf4Int4Half4: " + e.toString());
        }
    }

    private void verifyResultsPownHalf4Int4Half4(Allocation inBase, Allocation inExponent, Allocation out, boolean relaxed) {
        short[] arrayInBase = new short[INPUTSIZE * 4];
        Arrays.fill(arrayInBase, (short) 42);
        inBase.copyTo(arrayInBase);
        int[] arrayInExponent = new int[INPUTSIZE * 4];
        Arrays.fill(arrayInExponent, (int) 42);
        inExponent.copyTo(arrayInExponent);
        short[] arrayOut = new short[INPUTSIZE * 4];
        Arrays.fill(arrayOut, (short) 42);
        out.copyTo(arrayOut);
        StringBuilder message = new StringBuilder();
        boolean errorFound = false;
        for (int i = 0; i < INPUTSIZE; i++) {
            for (int j = 0; j < 4 ; j++) {
                // Extract the inputs.
                ArgumentsHalfIntHalf args = new ArgumentsHalfIntHalf();
                args.inBase = arrayInBase[i * 4 + j];
                args.inBaseDouble = Float16Utils.convertFloat16ToDouble(args.inBase);
                args.inExponent = arrayInExponent[i * 4 + j];
                // Figure out what the outputs should have been.
                Target target = new Target(Target.FunctionType.NORMAL, Target.ReturnType.HALF, relaxed);
                CoreMathVerifier.computePown(args, target);
                // Validate the outputs.
                boolean valid = true;
                if (!args.out.couldBe(Float16Utils.convertFloat16ToDouble(arrayOut[i * 4 + j]))) {
                    valid = false;
                }
                if (!valid) {
                    if (!errorFound) {
                        errorFound = true;
                        message.append("Input inBase: ");
                        appendVariableToMessage(message, args.inBase);
                        message.append("\n");
                        message.append("Input inExponent: ");
                        appendVariableToMessage(message, args.inExponent);
                        message.append("\n");
                        message.append("Expected output out: ");
                        appendVariableToMessage(message, args.out);
                        message.append("\n");
                        message.append("Actual   output out: ");
                        appendVariableToMessage(message, arrayOut[i * 4 + j]);
                        message.append("\n");
                        message.append("Actual   output out (in double): ");
                        appendVariableToMessage(message, Float16Utils.convertFloat16ToDouble(arrayOut[i * 4 + j]));
                        if (!args.out.couldBe(Float16Utils.convertFloat16ToDouble(arrayOut[i * 4 + j]))) {
                            message.append(" FAIL");
                        }
                        message.append("\n");
                        message.append("Errors at");
                    }
                    message.append(" [");
                    message.append(Integer.toString(i));
                    message.append(", ");
                    message.append(Integer.toString(j));
                    message.append("]");
                }
            }
        }
        assertFalse("Incorrect output for checkPownHalf4Int4Half4" +
                (relaxed ? "_relaxed" : "") + ":\n" + message.toString(), errorFound);
    }

    public void testPown() {
        checkPownFloatIntFloat();
        checkPownFloat2Int2Float2();
        checkPownFloat3Int3Float3();
        checkPownFloat4Int4Float4();
        checkPownHalfIntHalf();
        checkPownHalf2Int2Half2();
        checkPownHalf3Int3Half3();
        checkPownHalf4Int4Half4();
    }
}
