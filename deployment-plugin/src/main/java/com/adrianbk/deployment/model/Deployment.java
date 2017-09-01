package com.adrianbk.deployment.model;

public class Deployment<D extends Distribution> {
    D distribution;
    Iterable<Infrastructure> infrastructure;
}
