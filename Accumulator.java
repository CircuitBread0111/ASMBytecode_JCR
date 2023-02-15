////////////////////
//Accumulator.java
//Jerrin C. Redmon
//February, 15, 2023
////////////////////

//Imports
import static utils.Utilities.writeFile;
import org.objectweb.asm.*;
import org.objectweb.asm.Opcodes;

public class Accumulator{

    public static void main(String[] args){

        //Creates Class Writer
        ClassWriter cw = new ClassWriter(ClassWriter.COMPUTE_FRAMES);
        cw.visit(Opcodes.V11, Opcodes.ACC_PUBLIC,"Accumulation", null, "java/lang/Object",null);

        {//Initalizes Method Visitor
			MethodVisitor mv=cw.visitMethod(Opcodes.ACC_PUBLIC, "<init>", "()V", null, null);
			mv.visitCode();
			mv.visitVarInsn(Opcodes.ALOAD, 0);
			mv.visitMethodInsn(Opcodes.INVOKESPECIAL, "java/lang/Object", "<init>", "()V",false);
			mv.visitInsn(Opcodes.RETURN);
			mv.visitMaxs(1,1);
			mv.visitEnd();
		}//end of init

        {//Method Visitor Main
            MethodVisitor mv=cw.visitMethod(Opcodes.ACC_PUBLIC+Opcodes.ACC_STATIC, "main", "([Ljava/lang/String;)V", null, null);
            mv.visitCode();

            //Accumulator for Integers
            //uses loop and prompts user to give a number and add then print
            mv.visitTypeInsn(Opcodes.NEW, "java/util/Scanner");
            mv.visitInsn(Opcodes.DUP);
            mv.visitFieldInsn(Opcodes.GETSTATIC, "java/lang/System", "in", "Ljava/io/InputStream;");
            mv.visitMethodInsn(Opcodes.INVOKESPECIAL, "java/util/Scanner", "<init>", "(Ljava/io/InputStream;)V", false);
            mv.visitVarInsn(Opcodes.ASTORE, 1);
            mv.visitLdcInsn(0);
            mv.visitVarInsn(Opcodes.ISTORE, 2);
            //Begin of loop
            Label loop = new Label();
            mv.visitLabel(loop);
            mv.visitVarInsn(Opcodes.ALOAD, 1);
            mv.visitMethodInsn(Opcodes.INVOKEVIRTUAL, "java/util/Scanner", "nextInt", "()I", false);
            mv.visitVarInsn(Opcodes.ILOAD, 2);
            mv.visitInsn(Opcodes.IADD);
            mv.visitVarInsn(Opcodes.ISTORE, 2);
            mv.visitFieldInsn(Opcodes.GETSTATIC, "java/lang/System", "out", "Ljava/io/PrintStream;");
            mv.visitVarInsn(Opcodes.ILOAD, 2);
            mv.visitMethodInsn(Opcodes.INVOKEVIRTUAL, "java/io/PrintStream", "println", "(I)V", false);
            //Jumps back to the begin of loop
            mv.visitJumpInsn(Opcodes.GOTO, loop);

            //Return and End Visit
            mv.visitInsn(Opcodes.RETURN);
            mv.visitMaxs(0,0);
            mv.visitEnd();

        }//end of method visitor

        //Generates class file
        cw.visitEnd();
        byte[] b = cw.toByteArray();
        writeFile(b,"Accumulation.class");
        System.out.println("File Created!");

    }//end of main
}//end of class
