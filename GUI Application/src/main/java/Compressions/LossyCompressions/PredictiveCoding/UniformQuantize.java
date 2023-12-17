/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Other/File.java to edit this template
 */
package Compressions.LossyCompressions.PredictiveCoding;

/**
 *
 * @author abdelrahman
 */
import java.io.Serial;
import java.io.Serializable;

public class UniformQuantize implements Serializable {
    @Serial
    private static final long serialVersionUID = 2L;

    public UniformQuantize(int start, int end, int Q, int Qe) {
        this.start = start;
        this.end = end;
        this.Q = Q;
        this.Qe = Qe;
    }

    public int start;
    public int end;
    public int Q;
    public int Qe;
}
