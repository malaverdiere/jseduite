/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package fr.unice.i3s.modalis.jSeduite.technical.image.helper;

import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

/**
 *
 * @author mosser
 */
@WebService()
public class PictureSet {

    /** Merge two URL set into a single one
     * @param first the first set [f_1 ... f_x]
     * @param second the second set [s_1 ... s_y]
     * @return a set [f_1, s_1, f_2, s_2, ...]
     */
    @WebMethod(operationName = "merge")
    public URL[] merge(@WebParam(name = "first") URL[] first,
            @WebParam(name = "second") URL[] second) {
        first = this.jerk(first);
        second = this.jerk(second);
        int cliff = Math.min(first.length, second.length);
        ArrayList<URL> result = new ArrayList<URL>();
        for(int i= 0; i < cliff; i++) {
            result.add(first[i]); result.add(second[i]);
        }
        if (first.length > cliff) {
            for(int i = cliff; i < first.length; i++){
                result.add(first[i]);
            }   
        }
        if (second.length > cliff) {
            for(int i = cliff; i < second.length; i++){
                result.add(second[i]);
            }
        }
        return result.toArray(new URL[result.size()]);
    }

    /** Truncate an URL set to fit a given size
     * @param set the input set
     * @param count the expected maximum size
     * @return a set with 'count' element as maximum size (less possible)
     */
    @WebMethod(operationName = "truncate")
    public URL[] truncate(@WebParam(name = "set") URL[] set,
            @WebParam(name = "count") int count) {
        set = this.jerk(set);
        if (set.length <= count)
            return set;
        URL[] result = new URL[count];
        for(int i= 0; i < count; i++)
            result[i] = set[i];
        return result;
    }


    /** Shuffle a given URL set
     * @param set the URL set to shuffle
     * @return a shuffled url set
     */
    @WebMethod(operationName = "shuffle")
    public URL[] shuffle(@WebParam(name = "set") URL[] set) {
        set = this.jerk(set);
        ArrayList<URL> result = new ArrayList<URL>();
        for(URL u: set)
            result.add(u);
        Collections.shuffle(result);
        return result.toArray(new URL[result.size()]);
    }

    /** Jerk the null content of a set (added by ... who no who ... :@)
     * @param input
     * @return
     */
    private URL[] jerk(URL[] input) {
        ArrayList<URL> result = new ArrayList<URL>();
        for(URL u: input) {
            if (u != null)
                result.add(u);
        }
        return result.toArray(new URL[result.size()]);
    }

}
