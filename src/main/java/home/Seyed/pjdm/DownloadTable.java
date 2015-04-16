package home.Seyed.pjdm;

import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;
import javax.swing.JProgressBar;
import javax.swing.table.AbstractTableModel;
import jdlib.Download;

/**
 * This is DownloadTable class that update & manage download table...
 *
 * @author SMA74/9031806
 */
public class DownloadTable extends AbstractTableModel implements Observer {

	private static final String[] Names = {"نام فایل ", "درصد دانلود ", "اندازه فایل ", "سرعت دانلود "};
	private static final Class[] Types = {String.class, JProgressBar.class, String.class, String.class};
	private ArrayList Array = new ArrayList();

	/**
	 * Add new Download.
	 *
	 * @param an Download Object
	 */
	public void addDownload(Download download){
		download.addObserver(this);
		Array.add(download);
		fireTableRowsInserted(getRowCount() - 1, getRowCount() - 1);
	}

	/**
	 * Remove a download
	 *
	 * @param row Row number to remove from list.
	 */
	public void removeDownload(int row){
		Array.remove(row);
		fireTableRowsDeleted(row, row);
	}

	/*
	 * Return a download.
	 * @param row the number of row.
	 */
	public Download getDownload(int row){
		return (Download) Array.get(row);
	}

	/*
	 * Return the number of simultaneous downloads.
	 */
	public int getDownloadingCount(){
		int count = 0;
		for (int i = 0; i < Array.size(); i++)
			if ("Downloading".equals(((Download) (Array.get(i))).getStatus()))
				count++;
		return count;
	}

	/**
	 * Return column count of table.
	 */
	@Override
	public int getColumnCount(){
		return Names.length;
	}

	/**
	 * Return a name column.
	 *
	 * @param column
	 */
	@Override
	public String getColumnName(int column){
		return Names[column];
	}

	/**
	 * Return a class column.
	 *
	 * @param column
	 */
	@Override
	public Class getColumnClass(int column){
		return Types[column];
	}

	/**
	 * Return row count.
	 */
	@Override
	public int getRowCount(){
		return Array.size();
	}

	/**
	 * Return value for a row and column.
	 *
	 * @param row
	 * @param column
	 */
	@Override
	public Object getValueAt(int row, int column){
		Download download = (Download) Array.get(row);
		switch (column) {
			case 0: // File-Name column
				return download.getFileName();
			case 2: // Size column
				long size = download.getSize();
				String Size = null;
				if (size >= 1024)
					Size = (float) ((int) (new Float(size / 1024.0) * 100)) / 100 + " KB";
				if (size >= 1024 * 1024)
					Size = (float) ((int) (new Float((size / (1024 * 1024.0))) * 100)) / 100 + " MB";
				if (size >= 1024 * 1024 * 1024)
					Size = (float) ((int) (new Float((size / (1024 * 1024 * 1024.0))) * 100)) / 100 + " GB";
				return (size == -1) ? "" : Size;//Integer.toString(size);
			case 1: // Progress column
				if (download.getStatus().equals("Canceled"))
					return new Float("0");
				return new Float(download.getProgress());
			case 3: // Speed column
				if (download.getStatus().equals("Complete") || download.getProgress() >= 100)
					return "کامل شد";
				else if (download.getStatus().equals("Error"))
					return "خطا";
				else if (download.getStatus().equals("Paused"))
					return "متوقف";
				else if (download.getStatus().equals("Canceled"))
					return "لغو شد";
				return download.getSpeed();
		}
		return "";
	}

	/**
	 * Update method inform to Download Object any change.
	 *
	 * @param o   observable object.
	 * @param arg argument.
	 */
	@Override
	public void update(Observable o, Object arg){
		int index = Array.indexOf(o);
		fireTableRowsUpdated(index, index);
	}
}