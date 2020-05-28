package venus

import kotlinx.cli.ArgParser
import kotlinx.cli.ArgType
import kotlinx.cli.default
import kotlinx.cli.required
import venus.assembler.Assembler
import venus.linker.Linker
import venus.riscv.insts.*
import venus.simulator.Simulator
import java.io.File

fun init() {
    add;
    addi;
    and;
    andi;
    auipc;
    beq;
    bge;
    bgeu;
    blt;
    bltu;
    bne;
    div;
    divu;
    ecall;
    jal;
    jalr;
    lb;
    lbu;
    lh;
    lhu;
    lui;
    lw;
    mul;
    mulh;
    mulhsu;
    mulhu;
    or;
    ori;
    rem;
    remu;
    sb;
    sh;
    sll;
    slli;
    slt;
    slti;
    sltiu;
    sltu;
    sra;
    srai;
    srl;
    srli;
    sub;
    sw;
    xor;
    xori;
}

fun runText(program : String) {    
    val assemble = Assembler.assemble(program)
    val link = Linker.link(listOf(assemble.prog))
    
    if (!assemble.errors.isEmpty()) {
        System.err.println(assemble.errors)
    }
    
    val sim = Simulator(link)
    sim.run()
}

fun runFile(path : String) {
    runText(File(path).readText());
}

fun main(args : Array<String>) {
    init();
    
    val parser = ArgParser("Venus")
    val input by parser.argument(ArgType.String, description = "Input")
    val direct by parser.option(ArgType.Boolean, shortName = "c").default(false)

    parser.parse(args)
    
    if (direct) runText(input)
    else runFile(input)
}