package com.example.timesheet.service;

public class SequenceException extends Throwable {
  // public SequenceException(String s) {
  private static final long serialVersionUID = 1L;

  private String errCode;
  private String errMsg;

  //get, set...
  public SequenceException(String errMsg) {
    this.errMsg = errMsg;
  }
  //}
}
