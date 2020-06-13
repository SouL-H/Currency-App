// MEHMET https://github.com/SouL-H/
package currency;

import com.github.barismeral.dovizAPI.Currency;
import com.github.barismeral.dovizAPI.CurrencyFactory;
import com.github.barismeral.dovizAPI.Moneys;
import java.awt.Color;
import java.text.SimpleDateFormat;

import javax.swing.JPanel;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.DateAxis;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYItemRenderer;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.data.time.Month;
import org.jfree.data.time.TimeSeries;
import org.jfree.data.time.TimeSeriesCollection;
import org.jfree.data.xy.XYDataset;
import org.jfree.ui.ApplicationFrame;
import org.jfree.ui.RectangleInsets;
import java.text.SimpleDateFormat;


/**
 * An example of a time series chart.  For the most part, default settings are 
 * used, except that the renderer is modified to show filled shapes (as well as 
 * lines) at each data point.
 * <p>
 * IMPORTANT NOTE:  THIS DEMO IS DOCUMENTED IN THE JFREECHART DEVELOPER GUIDE.
 * DO NOT MAKE CHANGES WITHOUT UPDATING THE GUIDE ALSO!!
 */
public class islem extends ApplicationFrame {
        static CurrencyFactory factory = new CurrencyFactory(Moneys.US_DOLLAR);
        private static Currency cur = factory.getCurrency(); 
        static CurrencyFactory factory1 = new CurrencyFactory(Moneys.EURO);
        private static Currency cur1 = factory1.getCurrency();

    public islem(String title) {
        super(title);
        XYDataset dataset = createDataset();
        JFreeChart chart = createChart(dataset);
        ChartPanel chartPanel = new ChartPanel(chart, false);
        chartPanel.setPreferredSize(new java.awt.Dimension(500, 270));
        chartPanel.setMouseZoomable(true, false);
        setContentPane(chartPanel);
    }

    /**
     * Creates a chart.
     * 
     * @param dataset  a dataset.
     * 
     * @return A chart.
     */
    private static JFreeChart createChart(XYDataset dataset) {
        

        JFreeChart chart = ChartFactory.createTimeSeriesChart(
            "Doviz Alış Satış Grafiği",  // title
            "Zaman",             // x-axis label
            "Fiyat",   // y-axis label
            dataset,            // data
            true,               // create legend?
            true,               // generate tooltips?
            false               // generate URLs?
        );

        chart.setBackgroundPaint(Color.white);

        XYPlot plot = (XYPlot) chart.getPlot();
        plot.setBackgroundPaint(Color.lightGray);
        plot.setDomainGridlinePaint(Color.white);
        plot.setRangeGridlinePaint(Color.white);
        plot.setAxisOffset(new RectangleInsets(5.0, 5.0, 5.0, 5.0));
        plot.setDomainCrosshairVisible(true);
        plot.setRangeCrosshairVisible(true);
        
        XYItemRenderer r = plot.getRenderer();
        if (r instanceof XYLineAndShapeRenderer) {
            XYLineAndShapeRenderer renderer = (XYLineAndShapeRenderer) r;
            renderer.setBaseShapesVisible(true);
            renderer.setBaseShapesFilled(true);
        }
        
        DateAxis axis = (DateAxis) plot.getDomainAxis();
        axis.setDateFormatOverride(new SimpleDateFormat("MMM-yyyy"));
        
        return chart;

    }
    private static XYDataset createDataset(){
        Float DolarAlis[]=new Float[10];
        Float DolarSatis[]=new Float[10];
        int ZamanYil[]=new int[10];
        int ZamanAy[]=new int[10];
        float dolaralis1;
        float dolarsatis1;
        dolaralis1 = cur.getBuyingPrice();   // 5.3672
        dolarsatis1 = cur.getSellingPrice();
        String zaman = cur.getDate();
        String isim = cur.getName();
        Float EuroAlis[]=new Float[10];
        Float EuroSatis[]=new Float[10];
        int euroZamanYil[]=new int[10];
        int euroZamanAy1[]=new int[10];
        float euroalis1;
        float eurosatis1;
        euroalis1 = cur1.getBuyingPrice();   // 5.3672
        eurosatis1 = cur1.getSellingPrice();
        String eurozaman = cur1.getDate();
        String euroisim = cur1.getName();

        for (int i = 0; i < 10; i++) {
        DolarAlis[i] = dolaralis1;
        DolarSatis[i] = dolarsatis1; 
        ZamanAy[i] = Integer.parseInt(zaman.substring(0,2));
        ZamanYil[i] = Integer.parseInt(zaman.substring(6,10));
        EuroAlis[i] = euroalis1;
        EuroSatis[i] = eurosatis1; 
        euroZamanAy1[i] = Integer.parseInt(zaman.substring(0,2));
        euroZamanYil[i] = Integer.parseInt(zaman.substring(6,10));

        }
        TimeSeries s1 = new TimeSeries(isim +" "+"Alış Fiyati", Month.class);
        TimeSeries s2 = new TimeSeries(isim +" "+"Satis Fiyati", Month.class);
        TimeSeries s3 = new TimeSeries(euroisim +" "+"Salis Fiyati", Month.class);
        TimeSeries s4 = new TimeSeries(euroisim +" "+"Salis Fiyati", Month.class);

        for (int i = 0; i < 10; i++) {
        s1.add(new Month(((ZamanAy[i]-3)+i), ZamanYil[i]), DolarAlis[i]);
        s2.add(new Month(((ZamanAy[i]-3)+i), ZamanYil[i]), DolarSatis[i]);
        s3.add(new Month(((euroZamanAy1[i]-3)+i), euroZamanYil[i]), EuroAlis[i]);
        s4.add(new Month(((euroZamanAy1[i]-3)+i), euroZamanYil[i]), EuroSatis[i]);
        }


        TimeSeriesCollection dataset = new TimeSeriesCollection();
        dataset.addSeries(s1);
        dataset.addSeries(s2);
        dataset.addSeries(s3);
        dataset.addSeries(s4);
        return dataset;

    }

    /**
     * Creates a panel for the demo (used by SuperDemo.java).
     * 
     * @return A panel.
     */
    public static JPanel createDemoPanel() {
        JFreeChart chart = createChart(createDataset());
        return new ChartPanel(chart);
    }

}