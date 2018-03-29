/**
 * 
 */
package tools;

import java.util.Comparator;
import java.util.Map;

/**
 * @author ar-weichang.chen
 * @create-time 2015/02/03 15:44:56
 */
public class MapSortDouble implements Comparator<Map.Entry<String, Double>> {

	public int compare(Map.Entry<String, Double> m, Map.Entry<String, Double> n) {
		return n.getValue().compareTo(m.getValue()) ;
	}

}
