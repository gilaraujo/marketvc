package br.usp.marketvc.config;

public interface Default {
// number of DB connections
//public int dbconnections = 5;

// directory to store data
//public String dropboxPath = "/tmp";

public String[] companies = {"MSFT","GOOG","AAPL"};

// space
public double defaultFunds = 1000;

// servlets
public int INSERT = 0;
public int EDIT = 1;
public int UPDATE = 2;
public int LOGIN = 3;
public int LOGOUT = 4;
public int VIEW_PROFILE = 5;
public int VIEW_FUNDS = 6;
public int VIEW_INVESTMENTS = 7;

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

