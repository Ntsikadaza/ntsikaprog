package tvseriesmanage;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class SeriesManagerTest {

    private SeriesManager manager;

    @Before
    public void setUp() {
        manager = new SeriesManager();
        // Add initial series for testing
        manager.captureSeries(new SeriesModel(0, "Breaking Bad", 16, 62));
        manager.captureSeries(new SeriesModel(0, "Friends", 12, 236));
    }

    @Test
    public void testCaptureSeries() {
        int initialSize = manager.getSeriesList().size();
        manager.captureSeries(new SeriesModel(0, "Stranger Things", 14, 34));
        assertEquals(initialSize + 1, manager.getSeriesList().size());

        SeriesModel added = manager.searchSeries(initialSize + 1); // auto-assigned ID
        assertNotNull(added);
        assertEquals("Stranger Things", added.getName());
    }

    @Test
    public void testSearchSeries() {
        SeriesModel result = manager.searchSeries(1); // ID of first series
        assertNotNull(result);
        assertEquals("Breaking Bad", result.getName());
    }

    @Test
    public void testUpdateSeries() {
        boolean updated = manager.updateSeries(2, "Friends", 13, 240); // Update second series
        assertTrue(updated);

        SeriesModel updatedSeries = manager.searchSeries(2);
        assertEquals(13, updatedSeries.getAgeRestriction());
        assertEquals(240, updatedSeries.getEpisodes());
    }

    @Test
    public void testDeleteSeries() {
        boolean deleted = manager.deleteSeries(1); // Delete first series
        assertTrue(deleted);
        assertNull(manager.searchSeries(1));
    }

    @Test
    public void testPrintReport() {
        String report = manager.printReportString();
        assertTrue(report.contains("Breaking Bad"));
        assertTrue(report.contains("Friends"));

        // After deleting a series
        manager.deleteSeries(1);
        report = manager.printReportString();
        assertFalse(report.contains("Breaking Bad"));
        assertTrue(report.contains("Friends"));
    }
}