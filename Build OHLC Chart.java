/*

When visualizing market data over a long period of time, it is often helpful to build an Open-high-low-close (OHLC) chart. However, to build an OHLC chart you first need to prepare some data. For each financial instrument consider each day when it was traded, and find the following prices the instrument had that day:

open price: the price of the first trade of the day;
high price: the highest trade of the day;
low price: the lowest trade of the day;
close price: the price of the last trade of the day.
Given a stream of trade data ordered by time, write a program to compute the OHLC by day and instrument (see output section for the format details). If two trades happen to have equal timestamps, then their order is determined by the order of their timestamps in the timestamp array.

Example

For

timestamp = [1450625399, 1450625400, 1450625500, 
             1450625550, 1451644200, 1451690100, 1451691000]
instrument = ["HPQ", "HPQ", "HPQ", "HPQ", "AAPL", "HPQ", "GOOG"],
side = ["sell", "buy", "buy", "sell", "buy", "buy", "buy"],
price = [10, 20.3, 35.5, 8.65, 20, 10, 100.35] and
size = [10, 1, 2, 3, 5, 1, 10], the output should be

dailyOHLC(timestamp, instrument, side, price, size) = 
[["2015-12-20", "HPQ", "10.00", "35.50", "8.65", "8.65"],
 ["2016-01-01", "AAPL", "20.00", "20.00", "20.00", "20.00"],
 ["2016-01-01", "GOOG", "100.35", "100.35", "100.35", "100.35"],
 ["2016-01-01", "HPQ", "10.00", "10.00", "10.00", "10.00"]]
Input/Output

[time limit] 3000ms (java)
[input] array.integer timestamp

A nondecreasing sequence of positive integers. timestamp[i] stands for the Unix time when the ith trade was made.

Constraints:
1 ≤ timestamp.length ≤ 50,
666 ≤ timestamp[i] < 2 · 109.

[input] array.string instrument

Array of the same length as timestamp. instrument[i] is the ticker symbol (identifier) for the financial instrument taking part in the ith trade.

Constraints:
instrument.length = timestamp.length,
1 ≤ instrument[i].length ≤ 4.

[input] array.string side

Array of the same length as timestamp. side[i] equals either "buy" or "sell" depending on whether instrument[i] was bought or sold during the ith trade.

Constraints:
side.length = timestamp.length.

[input] array.float price

Array of the same length as timestamp. price[i] is the price of the instrument[i] during the ith trade. It is guaranteed that price[i] has no more than two digits after the decimal point.

Constraints:
price.length = timestamp.length,
0.5 ≤ price[i] ≤ 100.5.

[input] array.integer size

Array of the same length as timestamp. size[i] equals the number of shares of the instrument[i] traded during the ith trade.

Constraints:
size.length = timestamp.length,
1 ≤ size[i] ≤ 5 · 105.

[output] array.array.string

The ith row of the output should contain the following elements:

output[i][0] - local sever date in the YYYY-MM-DD format;
output[i][1] - a ticker symbol for some instrument;
output[i][2] - a string corresponding to the open price;
output[i][3] - a string corresponding to the high price;
output[i][4] - a string corresponding to the low price;
output[i][5] - a string corresponding to the close price.
Each string corresponding to the price should contain exactly two digits after the decimal point and at least one digit before.

For each unique pair of a date and an instrument present in the inputs, such that there was a trade of that instrument on that day, there should be exactly one row in the output.

Output rows should be ordered by dates. Rows corresponding to the same date should be ordered in lexicographical order for ticker symbols.

*/



class InstrumentDate implements Comparable<InstrumentDate>{
	String instrument;
	String date;
	public InstrumentDate(String instrument, int timestamp) {
		this.instrument = instrument;
		this.date = formatter.format(new java.util.Date((long)timestamp*1000));
	}
	public int hashCode(){
		return instrument.hashCode() + date.hashCode();
	}
	public boolean equals(InstrumentDate i1, InstrumentDate i2) {
		return i1.instrument.equals(i2.instrument) && i1.date.equals(i2.date);
	}
	public int compareTo(InstrumentDate i2) {
		if(this.date.equals(i2.date)) return this.instrument.compareTo(i2.instrument);
		return this.date.compareTo(i2.date);
	}
}
class OHLC {
	double openPrice, highPrice, lowPrice, closePrice;
	public OHLC(double price) {
		openPrice = highPrice = lowPrice = closePrice = price;
	}
	public void insert(double price) {
		closePrice = price;
		highPrice = Math.max(highPrice, price);
		lowPrice = Math.min(lowPrice, price);
	}
}
static SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
String[][] dailyOHLC(int[] timestamp, String[] instrument, String[] side, double[] price, int[] size) {
	Map<InstrumentDate, OHLC> map = new TreeMap<>();

	for(int i = 0; i < timestamp.length; i++) {
		String instru = instrument[i];
		int ts = timestamp[i];
		InstrumentDate instrudate = new InstrumentDate(instru, ts);
		if(!map.containsKey(instrudate)) map.put(instrudate, new OHLC(price[i]));
		else {
			OHLC ohlc = map.get(instrudate);
			ohlc.insert(price[i]);
		}
	}
	String[][] res = new String[map.size()][6];
	int i = 0;
	for(Map.Entry<InstrumentDate, OHLC> entry : map.entrySet()) {
		res[i][0] = entry.getKey().date;
		res[i][1] = entry.getKey().instrument;
		res[i][2] = String.format("%.2f", entry.getValue().openPrice);
		res[i][3] = String.format("%.2f", entry.getValue().highPrice);
		res[i][4] = String.format("%.2f", entry.getValue().lowPrice);
		res[i][5] = String.format("%.2f", entry.getValue().closePrice);
		i++;
	}
	return res;
}