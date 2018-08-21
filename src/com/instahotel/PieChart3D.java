package com.instahotel;

import javax.swing.JPanel;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PiePlot3D;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.general.PieDataset;
import org.jfree.ui.ApplicationFrame;
public class PieChart3D 
{

private JPanel createDemoPanel() {
	JFreeChart chart = createChart(createDataset());
	return new ChartPanel(chart);
}

private JFreeChart createChart(PieDataset dataset) {
	JFreeChart chart = ChartFactory.createPieChart3D(
			"Mobile Sales" , // chart title
			dataset , // data
			true , // include legend
			true,
			false);
	final PiePlot3D plot = (PiePlot3D) chart.getPlot();
	plot.setForegroundAlpha(0.80f);
	plot.setInteriorGap(0.0f);
	plot.setDepthFactor(0.6);
	
	return chart;
}

private PieDataset createDataset() {
	DefaultPieDataset dataset = new DefaultPieDataset( );
	dataset.setValue( "IPhone 5s" , new Double( 20 ) );
	dataset.setValue( "SamSung Grand" , new Double( 7 ) );
	dataset.setValue( "MotoG" , new Double( 5 ) );
	dataset.setValue( "Nokia Lumia" , new Double( 8 ) );
	dataset.setValue("Visnu", new Double(90));
	return dataset;
}
}