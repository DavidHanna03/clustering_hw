import net.sf.javaml.clustering.Clusterer;
import net.sf.javaml.clustering.KMeans;
import net.sf.javaml.clustering.evaluation.ClusterEvaluation;
import net.sf.javaml.clustering.evaluation.SumOfSquaredErrors;
import net.sf.javaml.core.Dataset;
import net.sf.javaml.core.Instance;
import net.sf.javaml.tools.data.FileHandler;
import net.sf.javaml.distance.EuclideanDistance;
import net.sf.javaml.clustering.DensityBasedSpatialClustering;
import net.sf.javaml.clustering.FuzzyKMeans;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;

public class App {

    public static void main(String[] args) {
        App app = new App();
        try {
            Dataset data = FileHandler.loadDataset(new File("iris.data"), 4, ",");
            app.runClustering(data);
        } catch (IOException e) {
            System.err.println("Error loading the dataset: " + e.getMessage());
        }
    }

    public void runClustering(Dataset data) {
        // KMeans Clustering
        System.out.println("KMeans Clustering:");
        clusterAndEvaluate(new KMeans(3, 1000), data);

        // DBSCAN Clustering
        System.out.println("DBSCAN Clustering:");
        clusterAndEvaluate(new DensityBasedSpatialClustering(0.2, 5, new EuclideanDistance()), data);

        // Fuzzy K-Means Clustering
        System.out.println("Fuzzy K-Means Clustering:");
        clusterAndEvaluate(new FuzzyKMeans(3, 2.0), data);
    }

    public void clusterAndEvaluate(Clusterer clusterer, Dataset data) {
        Dataset[] clusters = clusterer.cluster(data);
        // Print out clustering results
        for (int i = 0; i < clusters.length; i++) {
            System.out.println("Cluster " + (i + 1) + ": " + Arrays.toString(clusters[i].toArray()));
        }
        // Evaluation
        ClusterEvaluation sse = new SumOfSquaredErrors();
        double score = sse.score(clusters);
        System.out.println("Evaluation Score (SSE): " + score);
        System.out.println("----------------------------");
    }
}
