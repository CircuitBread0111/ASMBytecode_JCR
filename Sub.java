////////////////////
//Sub.java
//Jerrin C. Redmon
//February, 15, 2023
////////////////////

//Imports
import static utils.Utilities.writeFile;
import org.objectweb.asm.*;
import org.objectweb.asm.Opcodes;

public class Sub{

    public static void main(String[] args){

        //Creates Class Writer
        ClassWriter cw = new ClassWriter(ClassWriter.COMPUTE_FRAMES);
        cw.visit(Opcodes.V1_8, Opcodes.ACC_PUBLIC,"SubNums", null, "java/lang/Object",null);

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

            //Subtracting Integers
            mv.visitLdcInsn(7);
            mv.visitLdcInsn(3);
            mv.visitInsn(Opcodes.ISUB);
            mv.visitVarInsn(Opcodes.ISTORE, 1);
            mv.visitFieldInsn(Opcodes.GETSTATIC, "java/lang/System", "out", "Ljava/io/PrintStream;");
            mv.visitVarInsn(Opcodes.ILOAD, 1);
            mv.visitMethodInsn(Opcodes.INVOKEVIRTUAL, "java/io/PrintStream", "println", "(I)V", false);

            //Subtracting Longs
            mv.visitLdcInsn(4096L);
            mv.visitLdcInsn(128L);
            mv.visitInsn(Opcodes.LSUB);
            mv.visitVarInsn(Opcodes.LSTORE, 1);
            mv.visitFieldInsn(Opcodes.GETSTATIC, "java/lang/System", "out", "Ljava/io/PrintStream;");
            mv.visitVarInsn(Opcodes.LLOAD, 1);
            mv.visitMethodInsn(Opcodes.INVOKEVIRTUAL, "java/io/PrintStream", "println", "(J)V", false);

            //Subtracting Floats
            mv.visitLdcInsn(8.73f);
            mv.visitLdcInsn(5.99f);
            mv.visitInsn(Opcodes.FSUB);
            mv.visitVarInsn(Opcodes.FSTORE, 1);
            mv.visitFieldInsn(Opcodes.GETSTATIC, "java/lang/System", "out", "Ljava/io/PrintStream;");
            mv.visitVarInsn(Opcodes.FLOAD, 1);
            mv.visitMethodInsn(Opcodes.INVOKEVIRTUAL, "java/io/PrintStream", "println", "(F)V", false);

            //Subtracting Doubles
            mv.visitLdcInsn(844.22);
            mv.visitLdcInsn(424.45);
            mv.visitInsn(Opcodes.DSUB);
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
        writeFile(b,"SubNums.class");
        System.out.println("File Created!");

    }//end of main
}//end of class
