
import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.function.*;
import java.util.regex.*;
import java.util.stream.*;

import static java.util.stream.Collectors.*;
//import java.util.stream.Collectors;

class Result {

    /*
     * Complete the 'processLogs' function below.
     *
     * The function is expected to return a STRING_ARRAY.
     * The function accepts following parameters:
     *  1. STRING_ARRAY logs
     *  2. INTEGER maxSpan
     */

    public static List<String> processLogs(List<String> logs, int maxSpan) {
        // Write your code here

        return logs.stream().map(e -> {
            String[] log = e.split("\\s+");
            return new logintimelogouttime(Integer.parseInt(log[0]),
                    Integer.parseInt(log[1]), log[2]);
        }).collect(groupingBy(logintimelogouttime::getId))
                .entrySet()
                .stream().filter(entry -> {
                   
                  	Map<String, Integer> map = entry.getValue().stream().collect(toMap(logintimelogouttime::getType,
                  			logintimelogouttime::getSigninseconds));
                    if (map.containsKey("sign-in") && map.containsKey("sign-out")) {
                        int singInTime = map.get("sign-in");
                        int singOutTime = map.get("sign-out");
                        return ((singOutTime - singInTime) <= maxSpan);
                    }
                    return false;

                }).map(Map.Entry::getKey).sorted().map(String::valueOf).collect(Collectors.toList());
    }

}

class logintimelogouttime {
 
    

    Integer id ;
    Integer signinseconds;
    String type;
    public void setType(String type) {
		this.type = type;
	}

    public String getType() {
        return type;
    }
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getSigninseconds() {
		return signinseconds;
	}

	public void setSigninseconds(Integer signinseconds) {
		this.signinseconds = signinseconds;
	}

	

    
    public logintimelogouttime(Integer id, Integer signinseconds, String type) {
        this.id = id;
        this.signinseconds = signinseconds;
        this.type = type;
    }



   
}

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        int logsCount = Integer.parseInt(bufferedReader.readLine().trim());

        List<String> logs = IntStream.range(0, logsCount).mapToObj(i -> {
            try {
                return bufferedReader.readLine();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        })
                .collect(toList());

        int maxSpan = Integer.parseInt(bufferedReader.readLine().trim());

        List<String> result = Result.processLogs(logs, maxSpan);

        bufferedWriter.write(
                result.stream()
                        .collect(joining("\n"))
                        + "\n"
        );

        bufferedReader.close();
        bufferedWriter.close();
    }
}