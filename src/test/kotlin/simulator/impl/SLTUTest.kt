package venus.simulator.impl

import org.junit.Test
import kotlin.test.assertEquals
import venus.riscv.Instruction
import venus.riscv.InstructionField
import venus.simulator.SimulatorState
import venus.simulator.DispatchTest
import venus.simulator.FieldTest
import venus.simulator.InstructionImplementation

class Test {
    @Test
    fun basicSLTU() {
        // sltu x3 x2 x1
        val inst = Instruction(0b00000000000100010011000110110011)
        val state = SimulatorState()
        state.setReg(2, 10)
        state.setReg(1, 20)
        SLTU.implementation(inst, state)
        assertEquals(1, state.getReg(3))

        state.setReg(2, 30)
        state.setReg(1, 20)
        SLTU.implementation(inst, state)
        assertEquals(0, state.getReg(3))
    }

    @Test
    fun equalSLTU() {
        // sltu x3 x2 x1
        val inst = Instruction(0b00000000000100010011000110110011)
        val state = SimulatorState()
        state.setReg(2, 0)
        state.setReg(1, 0)
        SLTU.implementation(inst, state)
        assertEquals(0, state.getReg(3))

        state.setReg(2, -3)
        state.setReg(1, -3)
        SLTU.implementation(inst, state)
        assertEquals(0, state.getReg(3))
    }

    @Test
    fun unsignedSLTU() {
        // sltu x3 x2 x1
        val inst = Instruction(0b00000000000100010011000110110011)
        val state = SimulatorState()
        state.setReg(2, 1000)
        state.setReg(1, -1)
        SLTU.implementation(inst, state)
        assertEquals(1, state.getReg(3))

        state.setReg(2, -1)
        state.setReg(1, 1000)
        SLTU.implementation(inst, state)
        assertEquals(0, state.getReg(3))

        state.setReg(2, 0)
        state.setReg(1, 1)
        SLTU.implementation(inst, state)
        assertEquals(1, state.getReg(3))

        state.setReg(2, 1)
        state.setReg(1, 0)
        SLTU.implementation(inst, state)
        assertEquals(0, state.getReg(3))
    }
}