package com.example.blackhack_machine.tuska.Keranjang;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.blackhack_machine.tuska.R;
import com.squareup.picasso.Picasso;

import java.util.List;


public class StatusTransaksiListAdapter extends ArrayAdapter<StatusTransaksiItem> {

    private List<StatusTransaksiItem> transaksiList;
    private Context mCtx;
    public StatusTransaksiListAdapter(List<StatusTransaksiItem> transaksiList, Context mCtx) {
        super(mCtx, R.layout.transaksi_list_item, transaksiList);
        this.transaksiList = transaksiList;
        this.mCtx = mCtx;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(mCtx);
        View listViewItem = inflater.inflate(R.layout.transaksi_list_item, null, true);
        TextView txtInvoice = listViewItem.findViewById(R.id.invoiceText);
        TextView txtTglPesan = listViewItem.findViewById(R.id.dateProjectText);
        TextView txtStatusPembayaran = listViewItem.findViewById(R.id.statusTransaksiText);
        TextView txtOrderId = listViewItem.findViewById(R.id.orderidText);
        ImageView imgTransaksi = listViewItem.findViewById(R.id.transaksiImage);
        TextView txtStatusPembayaran2 = listViewItem.findViewById(R.id.statusPembayaran);
        StatusTransaksiItem statusTransaksiItem = transaksiList.get(position);


        txtInvoice.setText(statusTransaksiItem.getInvoiceText());
        txtTglPesan.setText(statusTransaksiItem.getDateProjectText());
        txtStatusPembayaran.setText(statusTransaksiItem.getStatusPembayaranText());
        txtOrderId.setText(statusTransaksiItem.getorderidText());
        Picasso.get().load(statusTransaksiItem.getTransaksiImage())
                .placeholder(R.drawable.ic_wait)
                .resize(100, 90)
                .error(R.drawable.ic_wait)
                .into(imgTransaksi);
        txtStatusPembayaran2.setText(statusTransaksiItem.getstatusPembayaran());

        return listViewItem;
    }
}
