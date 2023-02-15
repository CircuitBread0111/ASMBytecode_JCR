////////////////////
//Coamp.java
//Jerrin C. Redmon
//February, 15, 2023
////////////////////

//Imports
import static utils.Utilities.writeFile;
import org.objectweb.asm.*;
import org.objectweb.asm.Opcodes;

public class Comp{

    public static void main(String[] args){

        //Creates Class Writer
        ClassWriter cw = new ClassWriter(ClassWriter.COMPUTE_FRAMES);
        cw.visit(Opcodes.V11, Opcodes.ACC_PUBLIC,"CompNums", null, "java/lang/Object",null);

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

            //Comparing Integers
            mv.visitLdcInsn(5);
            mv.visitVarInsn(Opcodes.ISTORE, 1);
            mv.visitVarInsn(Opcodes.ILOAD, 1);
            mv.visitLdcInsn(9);
            mv.visitVarInsn(Opcodes.ISTORE, 2);
            mv.visitVarInsn(Opcodes.ILOAD, 2);
            Label ifICompGt = new Label();
            Label endIfICompGt = new Label();
            mv.visitJumpInsn(Opcodes.IF_ICMPGT, ifICompGt);
            mv.visitFieldInsn(Opcodes.GETSTATIC, "java/lang/System", "out", "Ljava/io/PrintStream;");
            mv.visitVarInsn(Opcodes.ILOAD, 2);
            mv.visitMethodInsn(Opcodes.INVOKEVIRTUAL, "java/io/PrintStream", "println", "(I)V", false);
            mv.visitJumpInsn(Opcodes.GOTO, endIfICompGt);

            mv.visitLabel(ifICompGt);
            mv.visitFieldInsn(Opcodes.GETSTATIC, "java/lang/System", "out", "Ljava/io/PrintStream;");
            mv.visitVarInsn(Opcodes.ILOAD, 1);
            mv.visitMethodInsn(Opcodes.INVOKEVIRTUAL, "java/io/PrintStream", "println", "(I)V", false);

            mv.visitLabel(endIfICompGt);

            //Comparing Longs
            mv.visitLdcInsn(26l);
            mv.visitVarInsn(Opcodes.LSTORE, 1);
            mv.visitVarInsn(Opcodes.LLOAD, 1);
            mv.visitLdcInsn(17l);
            mv.visitVarInsn(Opcodes.LSTORE, 3);
            mv.visitVarInsn(Opcodes.LLOAD, 3);
            Label ifLCompGt = new Label();
            Label endIfLCompGt = new Label();
            mv.visitInsn(Opcodes.LCMP);
            mv.visitJumpInsn(Opcodes.IFGT, ifLCompGt);
            mv.visitFieldInsn(Opcodes.GETSTATIC, "java/lang/System", "out", "Ljava/io/PrintStream;");
            mv.visitVarInsn(Opcodes.LLOAD, 3);
            mv.visitMethodInsn(Opcodes.INVOKEVIRTUAL, "java/io/PrintStream", "println", "(J)V", false);
            mv.visitJumpInsn(Opcodes.GOTO, endIfLCompGt);

            mv.visitLabel(ifLCompGt);
            mv.visitFieldInsn(Opcodes.GETSTATIC, "java/lang/System", "out", "Ljava/io/PrintStream;");
            mv.visitVarInsn(Opcodes.LLOAD, 1);
            mv.visitMethodInsn(Opcodes.INVOKEVIRTUAL, "java/io/PrintStream", "println", "(J)V", false);
            mv.visitLabel(endIfLCompGt);

            //Return and End Visit
            mv.visitInsn(Opcodes.RETURN);
            mv.visitMaxs(0,0);
            mv.visitEnd();

        }//end of method visitor

        //Generates class file
        cw.visitEnd();
        byte[] b = cw.toByteArray();
        writeFile(b,"CompNums.class");
        System.out.println("File Created!");

    }//end of main
}//end of class
