package ie.gmit.sw;

import javax.swing.table.*;
public class TypeSummaryTableModel extends AbstractTableModel{
	private static final long serialVersionUID = 777L;
	private String[] cols = {"Col 1", "Col 2", "Col 3"};
	public static Object[][] data = new Object[ReceiveJar.Metrics.size()][3];
	
	public static Object[][] getMetricData(){
        int i = 0;
        for(metric m : ReceiveJar.Metrics.values()){            
            data[i][0] = m.getName();  
            data[i][1] = m.getOutDegree();  
            data[i][2] = m.getInDegree();            
            i++;
        } 

        return data;

    } 
	public int getColumnCount() {
        return cols.length;
    }
	
    public int getRowCount() {
        return data.length;
	}

    public String getColumnName(int col) {
    	return cols[col];
    }

    public Object getValueAt(int row, int col) {
        return data[row][col];
	}
   
    public Class<?> getColumnClass(int c) {
        return getValueAt(0, c).getClass();
	}
}