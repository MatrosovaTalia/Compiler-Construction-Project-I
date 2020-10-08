package simple;

import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.MethodVisitor;

import java.util.ArrayList;
import java.util.function.IntFunction;
import java.util.stream.Stream;

public class ElementCall extends ArrayList<IPrimary> implements ILexem{
    @Override
    public void emit(ClassWriter cw, MethodVisitor mv) {

    }

    @Override
    public <T> T[] toArray(IntFunction<T[]> generator) {
        return null;
    }

    @Override
    public Stream<IPrimary> stream() {
        return null;
    }

    @Override
    public Stream<IPrimary> parallelStream() {
        return null;
    }
}
