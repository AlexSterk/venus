package venus.simulator.impl

import venus.riscv.Instruction
import venus.riscv.InstructionField
import venus.simulator.SimulatorState
import venus.simulator.DispatchTest
import venus.simulator.FieldTest
import venus.simulator.InstructionImplementation

object SH {
    val implementation = object : InstructionImplementation {
        override operator fun invoke(inst: Instruction, state: SimulatorState) {
            val rs1: Int = inst.getField(InstructionField.RS1)
            val rs2: Int = inst.getField(InstructionField.RS2)
            val imm: Int = constructStoreImmediate(inst)
            state.mem.storeHalfWord(state.getReg(rs1) + imm, rs2)
        }
    }

    val tests: List<DispatchTest> = listOf(
        FieldTest(InstructionField.OPCODE, 0b0100011),
        FieldTest(InstructionField.FUNCT3, 0b001)
    )
}