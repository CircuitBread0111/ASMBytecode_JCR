//Imports
import static utils.Utilities.writeFile;
import org.objectweb.asm.*;
import org.objectweb.asm.Opcodes;

public class FizzBuzz {

    public static void main(String[] args){

        //Creates Class Writer
        ClassWriter cw = new ClassWriter(ClassWriter.COMPUTE_FRAMES);
        cw.visit(Opcodes.V11, Opcodes.ACC_PUBLIC,"FB", null, "java/lang/Object",null);

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
            //FizzBuzz
            Label loop = new Label();
            Label endLoop = new Label();
            Label fizz = new Label();
            Label buzz = new Label();
            Label num = new Label();
            mv.visitLdcInsn(1);
            mv.visitVarInsn(Opcodes.ISTORE, 1);
            mv.visitLabel(loop);
            mv.visitVarInsn(Opcode.ILOAD, 1);
            mv.visitLdcInsn(100);
            mv.visitJumpInsn(Opcodes.IF_ICMPGT, endLoop);
            mv.visitVarInsn(Opcodes.ILOAD, 1);
            mv.visitLdcInsn(3);
            mv.visitInsn(Opcodes.IREM, 1);
            mv.visitJumpInsn(Opcodes.IFEQ, fizz);
            mv.visitVarInsn(Opcodes.ILOAD, 1);
            mv.visitLdcInsn(5);
            mv.visitInsn(Opcodes.IREM, 1);
            mv.visitJumpInsn(Opcodes.IFEQ, buzz);


            mv.visitFieldInsn(Opcodes.GETSTATIC, "java/lang/System", "out", "Ljava/io/PrintStream;");
            mv.visitVarInsn(Opcodes.ILOAD, 1);
            mv.visitMethodInsn(Opcodes.INVOKEVIRTUAL, "java/io/PrintStream", "print", "(I)V", false);
            mv.visitJumpInsn(Opcodes.GOTO,);


            visitLabel(fizz);








            mv.visitLabel(endLoop);













            /*
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
            */



            //Return and End Visit
            mv.visitInsn(Opcodes.RETURN);
            mv.visitMaxs(0,0);
            mv.visitEnd();

        }//end of method visitor




        //Generates class file
        cw.visitEnd();
        byte[] b = cw.toByteArray();
        writeFile(b,"FB.class");
        System.out.println("File Created!");

    }//end of main
}//end of class
