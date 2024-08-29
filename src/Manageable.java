
import java.io.Serializable;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author MSI
 */
public interface Manageable extends Serializable {
    void add();
    void update();
    void delete();
    void display();
}
