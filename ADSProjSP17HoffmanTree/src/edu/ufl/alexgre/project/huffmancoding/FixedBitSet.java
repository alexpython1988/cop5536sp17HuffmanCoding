package edu.ufl.alexgre.project.huffmancoding;

import java.util.BitSet;

public class FixedBitSet extends BitSet{

    int fixedLength;

    public FixedBitSet(int fixedLength){
        super(fixedLength);
        this.fixedLength = fixedLength;
    }

    
}