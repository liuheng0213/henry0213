package basic.knowledge.henry.algorithm.InterverviewFromRenowedITCompany._06bigDatAndSpaceLimitaion;

import java.util.BitSet;

public class JavaBitSet implements BaseBitSet {

    private BitSet bitSet;

    public JavaBitSet() {
        this.bitSet = new BitSet();
    }

    public JavaBitSet(BitSet bitSet) {
        if (bitSet == null) {
            this.bitSet = new BitSet();
        } else {
            this.bitSet = bitSet;
        }
    }

    public void set(int bitIndex) {
        this.bitSet.set(bitIndex);
    }

    public void set(int bitIndex, boolean value) {
        this.bitSet.set(bitIndex, value);
    }

    public boolean get(int bitIndex) {
        return this.bitSet.get(bitIndex);
    }

    public void clear(int bitIndex) {
        this.bitSet.clear(bitIndex);
    }

    public void clear() {
        this.bitSet.clear();
    }

    public long size() {
        return this.bitSet.size();
    }

    public boolean isEmpty() {
        return this.isEmpty();
    }
}
