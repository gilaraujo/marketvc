package br.usp.marketvc.config;

public interface Default {
// number of DB connections
//public int dbconnections = 5;

// directory to store data
//public String dropboxPath = "/tmp";

public String[] companies = {"MSFT","PBR","GOOG"};

// space
public double defaultFunds = 1000;

public int hourInterval = 60;
public int dayInterval = 1440;

// servlets
public int INSERT = 0;
public int UPDATE = 2;
public int LOGIN = 3;
public int LOGOUT = 4;
public int VIEW_PROFILE = 5;
public int VIEW_FUNDS = 6;
public int VIEW_INVESTMENTS = 7;
public int LIST_INVESTMENTS = 8;
public int SELL_INVESTMENTS = 9;
public int LATEST_QUOTES = 10;
public int VIEW_ALL_STOCKS = 11;
public int LIST_ALL_STOCK = 12;
public int BUY_STOCK = 13;
public int VIEW_RECOMMENDED_STOCKS = 14;
public int VIEW_LATEST_QUOTES = 15;
public int LIST_RECOMMENDED_STOCKS = 16;
public int VIEW_BANK = 20;
public int LIST_DEBT = 21;
public int TAKE_LOAN = 22;
public int PAY_DEBT = 23;

/*
// user servlet
public int LOGIN = 3;
public int LOGOUT = 4;
public int CONFIRMLOGIN = 5;

// dropfile servlet
public int FILEUPLOAD = 100;
public int FILEDOWNLOAD = 101;
public int FILEREMOVE = 102;
public int MKDIR = 103;
public int RMDIR = 104;
public int LIST = 105;
*/
}

