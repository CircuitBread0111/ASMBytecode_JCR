////////////////////
//While.java
//Jerrin C. Redmon
//February, 15, 2023
////////////////////

//Imports
import static utils.Utilities.writeFile;
import org.objectweb.asm.*;
import org.objectweb.asm.Opcodes;

public class While{

    public static void main(String[] args){

        //Creates Class Writer
        ClassWriter cw = new ClassWriter(ClassWriter.COMPUTE_FRAMES);
        cw.visit(Opcodes.V11, Opcodes.ACC_PUBLIC,"WhileLoop", null, "java/lang/Object",null);

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

            //While loop
            //Counts down to Zero
            Label loop = new Label();
            Label endLoop = new Label();
            mv.visitLdcInsn(100);
            mv.visitVarInsn(Opcodes.ISTORE, 1);
            mv.visitLabel(loop);
            mv.visitVarInsn(Opcodes.ILOAD, 1);
            mv.visitJumpInsn(Opcodes.IFEQ, endLoop);
            //print
            mv.visitFieldInsn(Opcodes.GETSTATIC, "java/lang/System", "out", "Ljava/io/PrintStream;");
            mv.visitVarInsn(Opcodes.ILOAD, 1);
            mv.visitMethodInsn(Opcodes.INVOKEVIRTUAL, "java/io/PrintStream", "println", "(I)V", false);
            mv.visitIincInsn(1, -1);
            mv.visitJumpInsn(Opcodes.GOTO, loop);
            mv.visitLabel(endLoop);//end of loop

            //Return and End Visit
            mv.visitInsn(Opcodes.RETURN);
            mv.visitMaxs(0,0);
            mv.visitEnd();

        }//end of method visitor

        //Generates class file
        cw.visitEnd();
        byte[] b = cw.toByteArray();
        writeFile(b,"WhileLoop.class");
        System.out.println("File Created!");

    }//end of main
}//end of class
