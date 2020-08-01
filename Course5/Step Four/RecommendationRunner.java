import java.util.*;
/**
 * Write a description of RecommendationRunner here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class RecommendationRunner implements Recommender{
    public ArrayList<String> getItemsToRate (){
        ArrayList<String> list = MovieDatabase.filterBy(new TrueFilter());
        int n = list.size();
        Random rd = new Random();
        ArrayList<String> items = new ArrayList<String>();
        HashSet<Integer> s = new HashSet<Integer>();
        for(int i = 0; i < 10; i++){
            int index = rd.nextInt(n);
            while(s.contains(index)){
                index = rd.nextInt(n);
            }
            s.add(index);
            items.add(list.get(index));
        }
        return items;
    }
    
    private String getDuration(int minutes){
        String duration = "";
        int hr = minutes / 60;
        int mins = minutes % 60;
        if(hr == 0)
            duration += mins + "min";
        else if(mins == 0){
            duration += hr + "h";
        }
        else{
            duration += hr + "h " + mins + " min";
        }
        return duration;
    }
    
    private String addMovie(String id){
        String tabledata = "<td> <div class=\"a\">\n";
        tabledata += MovieDatabase.getTitle(id) + "(" + MovieDatabase.getYear(id) + ")\n";
        tabledata += "<figure>\n";
        tabledata += "<img src=\"" + MovieDatabase.getPoster(id).replace("http", "https") + "\" alt=\"" + MovieDatabase.getTitle(id) + "\">\n";
        tabledata += "</figure>\n</div>\n";
        tabledata += "<div class=\"b\">\n";
        tabledata += getDuration(MovieDatabase.getMinutes(id)) + " | " + MovieDatabase.getGenres(id) + "\n";
        tabledata += "</div>\n";
        tabledata += "</td>\n";
        
        return tabledata;
        
    }
    
    public void printRecommendationsFor (String webRaterID){
        String ratingfile, moviefile, rater_id;
        ratingfile = "data/ratings.csv";
        moviefile = "ratedmoviesfull.csv";
        rater_id = webRaterID;
        int minimalRaters = 3;
        int numSimilarRaters = 20;
        FourthRatings fourthRt = new FourthRatings(ratingfile);
        MovieDatabase.initialize(moviefile);
        ArrayList<Rating> list = fourthRt.getSimilarRatings(rater_id, numSimilarRaters, minimalRaters);
        //System.out.println("Found " + list.size() + " movies");
        Collections.sort(list, Collections.reverseOrder());
        /*for(Rating r : list){
            System.out.println(MovieDatabase.getMovie(r.getItem()) + " : " + r.getValue());
        }*/
        
        StringBuilder sb = new StringBuilder();
        sb.append("<html>");
        sb.append("<head><title>Recommendation System</title>");
        String style = "<style> \n"+
"                            figure { \n"+
"                            }\n"+
                            
"                            body{\n"+
"                             background-color: #ffcccc;\n"+
"                            }\n"+
"                            figcaption {\n"+
"                              font-style: italic;\n"+
"                              font-weight: bolder;\n"+
"                              padding: 2px;\n"+
                              
"                            }\n"+
"                            img{\n"+
                              "width : 250px;\n"+
"                              height: 200px;\n"+
"                            }\n"+
                            
"                            table, td, th {\n"+
"                              border: 1px solid black;\n"+
"                            }\n"+
                            
"                            div.a{\n"+
"                              text-align: center;\n"+
"                              font-weight: bolder;\n"+
"                              font-style: italic;\n"+
"                              font-size: 20px;\n"+
                            
"                            }\n"+
                            
"                            div.b{\n"+
"                              text-align: center;\n"+
"                              font-size: 17px;\n"+
"                            }\n"+
                            
"                            td{\n"+
"                              background-color: #ccffff;\n"+
"                            }\n"+
"                            </style>\n";
        sb.append(style);
        sb.append("</head>");
        sb.append("<body>");
        sb.append("<h1> Recommended Movies </h1>");
        if(list.size() == 0){
            sb.append("<h2> Sorry. Unable to find any recommendation for you!!!</h2>");
        }
        else{
            String table = "<table>\n";
            int i;
            for(i = 0; i < list.size() && i < 16; i++){
                if(i % 4 == 0){
                    table += "<tr>\n";
                }
                table += addMovie(list.get(i).getItem());
                if(i % 4 == 3){
                    table += "</tr>\n";
                }
            }
            i--;
            if(i % 4 != 3)
                table += "</tr>\n";
            sb.append(table);
            sb.append("</table>");
        }
        sb.append("</body>");
        sb.append("</html>");
        System.out.println(sb.toString());
    }
    
    public void runner(){
        System.out.println(getDuration(100));
        System.out.println(getDuration(200));
        System.out.println(getDuration(180));
    }
}
