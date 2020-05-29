package com.example.apscurso;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

public class AdapterTurma extends BaseAdapter {
    private List<Turma> listaTurmas;
    private LayoutInflater inflater;

    public AdapterTurma(Context context, List<Turma> lista ){
        this.listaTurmas = lista;
        this.inflater = LayoutInflater.from( context );
    }

    @Override
    public int getCount() {
        return listaTurmas.size();
    }

    @Override
    public Object getItem(int position) {
        return listaTurmas.get( position );
    }

    @Override
    public long getItemId(int position) {
        return listaTurmas.get( position ).getId() ;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ItemSuporte item;

        if( convertView == null ){
            convertView = inflater.inflate(R.layout.layout_lista, null);
            item = new ItemSuporte();
            item.tvNome = (TextView) convertView.findViewById(R.id.llTvNome);
            item.tvSala = (TextView) convertView.findViewById(R.id.llTvSala);
            item.fundoTela = convertView.findViewById(R.id.llLayout);

            convertView.setTag( item );

        }else{
            item = (ItemSuporte) convertView.getTag();
        }

        Turma turma = listaTurmas.get( position );
        item.tvNome.setText( turma.getNome() );
        item.tvSala.setText( turma.getSala() );

        if( position % 2 == 0 ){
            item.fundoTela.setBackgroundColor( Color.WHITE );
        }else {
            item.fundoTela.setBackgroundColor( Color.rgb( 230, 230, 230) );
        }


        return convertView;
    }

    private class ItemSuporte{
        TextView tvNome, tvSala;
        LinearLayout fundoTela;
    }
}
