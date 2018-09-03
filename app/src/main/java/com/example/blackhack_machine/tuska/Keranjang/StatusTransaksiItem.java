package com.example.blackhack_machine.tuska.Keranjang;

public class StatusTransaksiItem {
    String invoiceText, statusPembayaranText, dateProjectText, orderidText, transaksiImage, statusPembayaran ;

    public StatusTransaksiItem(String invoiceText, String statusPembayaranText, String dateProjectText, String orderidText, String transaksiImage, String statusPembayaran) {
        this.invoiceText = invoiceText;
        this.statusPembayaranText = statusPembayaranText;
        this.dateProjectText = dateProjectText;
        this.orderidText = orderidText;
        this.transaksiImage = transaksiImage;
        this.statusPembayaran = statusPembayaran;
    }

    public String getInvoiceText() {
        return invoiceText;
    }

    public String getStatusPembayaranText() {
        return statusPembayaranText;
    }

    public String getDateProjectText() {
        return dateProjectText;
    }

    public String getorderidText() {
        return orderidText;
    }

    public String getTransaksiImage() {
        return transaksiImage;
    }
    public String getstatusPembayaran() {
        return statusPembayaran;
    }

}
