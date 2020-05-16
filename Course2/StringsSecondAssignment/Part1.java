
/**
 * Write a description of Part1 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Part1 {
    public int findStopCodon(String dna, int startIndex, String stopCodon){
        int pos = dna.indexOf(stopCodon, startIndex + 3);
        while(pos != -1){
            if((pos - startIndex) % 3 == 0){
                return pos;
            }
            else{
                pos = dna.indexOf(stopCodon, pos + 1);
            }
        }
        return dna.length();
    }
    public String findGene(String dna){
        int startIndex = dna.indexOf("ATG");
        if(startIndex == -1)
            return "";
        int taaIndex = findStopCodon(dna, startIndex, "TAA");
        int tagIndex = findStopCodon(dna, startIndex, "TAG");
        int tgaIndex = findStopCodon(dna, startIndex, "TGA");
        int minIndex = Math.min(taaIndex, Math.min(tagIndex, tgaIndex));
        if(minIndex == dna.length())
            return "";
        return dna.substring(startIndex, minIndex + 3);
        
    }
    public void testFindGene(){
        System.out.println(findGene("ATGTAA"));
        System.out.println(findGene("ATGATAG"));
        System.out.println(findGene("ATGGATGA"));
        System.out.println(findGene("ATGGTAACTGAATAGATGTAA"));
        
    }
    public void printAllGenes(String dna){
        String gene = findGene(dna);
        while(true){
            if(gene.isEmpty())
                break;
            System.out.println(gene);
            gene = findGene(dna.substring(dna.indexOf(gene) + gene.length()));
        }
    }

}
