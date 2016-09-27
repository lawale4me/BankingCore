/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.bytecode.util;

import java.util.Random;

/**
 *
 * @author Ahmed
 */
public class GenerateNumbers 
{

    public static long generateRandom(int length) {
    Random random = new Random();
    char[] digits = new char[length];
    digits[0] = (char) (random.nextInt(9) + '1');
    for (int i = 1; i < length; i++) {
        digits[i] = (char) (random.nextInt(10) + '0');
    }
    return Long.parseLong(new String(digits));
}
    
}
