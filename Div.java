////////////////////
//Div.java
//Jerrin C. Redmon
//February, 15, 2023
////////////////////

//Imports
import static utils.Utilities.writeFile;
import org.objectweb.asm.*;
import org.objectweb.asm.Opcodes;

public class Div{

    public static void main(String[] args){

        //Creates Class Writer
        ClassWriter cw = new ClassWriter(ClassWriter.COMPUTE_FRAMES);
        cw.visit(Opcodes.V1_8, Opcodes.ACC_PUBLIC,"DivNums", null, "java/lang/Object",null);

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

            //Dividing Integers
            mv.visitLdcInsn(9);
            mv.visitLdcInsn(3);
            mv.visitInsn(Opcodes.IDIV);
            mv.visitVarInsn(Opcodes.ISTORE, 1);
            mv.visitFieldInsn(Opcodes.GETSTATIC, "java/lang/System", "out", "Ljava/io/PrintStream;");
            mv.visitVarInsn(Opcodes.ILOAD, 1);
            mv.visitMethodInsn(Opcodes.INVOKEVIRTUAL, "java/io/PrintStream", "println", "(I)V", false);

            //Dividing Longs
            mv.visitLdcInsn(1300L);
            mv.visitLdcInsn(130L);
            mv.visitInsn(Opcodes.LDIV);
            mv.visitVarInsn(Opcodes.LSTORE, 1);
            mv.visitFieldInsn(Opcodes.GETSTATIC, "java/lang/System", "out", "Ljava/io/PrintStream;");
            mv.visitVarInsn(Opcodes.LLOAD, 1);
            mv.visitMethodInsn(Opcodes.INVOKEVIRTUAL, "java/io/PrintStream", "println", "(J)V", false);

            //Dividing Floats
            mv.visitLdcInsn(5.32f);
            mv.visitLdcInsn(2.65f);
            mv.visitInsn(Opcodes.FDIV);
            mv.visitVarInsn(Opcodes.FSTORE, 1);
            mv.visitFieldInsn(Opcodes.GETSTATIC, "java/lang/System", "out", "Ljava/io/PrintStream;");
            mv.visitVarInsn(Opcodes.FLOAD, 1);
            mv.visitMethodInsn(Opcodes.INVOKEVIRTUAL, "java/io/PrintStream", "println", "(F)V", false);

            //Dividing Doubles
            mv.visitLdcInsn(235.64);
            mv.visitLdcInsn(34.44);
            mv.visitInsn(Opcodes.DDIV);
            mv.visitVarInsn(Opcodes.DSTORE, 1);
            mv.visitFieldInsn(Opcodes.GETSTATIC, "java/lang/System", "out", "Ljava/io/PrintStream;");
            mv.visitVarInsn(Opcodes.DLOAD, 1);
            mv.visitMethodInsn(Opcodes.INVOKEVIRTUAL, "java/io/PrintStream", "println", "(D)V", false);

            //Return and End Visit
            mv.visitInsn(Opcodes.RETURN);
            mv.visitMaxs(0,0);
            mv.visitEnd();

        }//end of method visitor

        //Generates class file
        cw.visitEnd();
        byte[] b = cw.toByteArray();
        writeFile(b,"DivNums.class");
        System.out.println("File Created!");

    }//end of main
}//end of class
