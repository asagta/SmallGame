package com.example.asaram.smallGame;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.Image;
import android.net.Uri;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.channels.FileChannel;

/**
 * Created by Asaram on 24-06-2020.
 */
public class FilePicker extends DialogFragment {
    public static final int PICKFILE_RESULT_CODE = 1;

    private Button btnChooseFile;
    private TextView tvItemPath;
    public static ImageView tvitem;
    public static EditText pname;
    private Uri fileUri;
    private String filePath;
    public static String p_name;
    DatabaseHandler db1;
    public FilePicker() {
        Log.d("FRAGMENT", "CLICKED");
    }

    @Override
    public View onCreateView(final LayoutInflater inflater, final ViewGroup container,
                             final Bundle savedInstanceState) {
        Log.d("FRAGMENT2", "CLICKED");
        //setClickonAddMore();
        View rootView = inflater.inflate(R.layout.add_players, container, false);
        btnChooseFile = (Button) rootView.findViewById(R.id.btn_choose_file);
        //tvItemPath = (ImageView) rootView.findViewById(R.id.tv_file_path);
        tvitem = (ImageView) rootView.findViewById(R.id.tv_file_path);
        setStyle(DialogFragment.STYLE_NORMAL, android.R.style.Theme_Black_NoTitleBar_Fullscreen);
        return inflater.inflate(R.layout.add_players, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        pname = (EditText) view.findViewById(R.id.editText);
        final Spinner bowl = (Spinner) view.findViewById(R.id.spinner4);
        final Spinner teamId = (Spinner) view.findViewById(R.id.spinner3);
        Button add = (Button) view.findViewById(R.id.button35);
        Button back = (Button) view.findViewById(R.id.button33);
        View btnChooseFile = view.findViewById(R.id.btn_choose_file);
        tvitem = (ImageView) view.findViewById(R.id.tv_file_path);

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(pname.getText().toString().isEmpty()||teamId.getSelectedItem().toString().isEmpty()||bowl.getSelectedItem().toString().isEmpty())
                    Toast.makeText(getActivity(),"FIELDS CANNOT BE LEFT BLANK",Toast.LENGTH_SHORT).show();
                else {
                    //SquadMgmt sq=new SquadMgmt();
                    ((SquadMgmt)getActivity()).AddToTeam(pname.getText().toString(), teamId.getSelectedItem().toString(), bowl.getSelectedItem().toString());
                    //db1.addPlayer(pname.getText().toString(), teamId.getSelectedItem().toString(), bowl.getSelectedItem().toString());
                    Toast.makeText(getActivity(),pname.getText().toString()+" is ADDED",Toast.LENGTH_SHORT).show();
                    pname.setText("");
                }
            }
        });
        btnChooseFile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("Choose File:", "CLICKED");
                Intent chooseFile = new Intent(Intent.ACTION_GET_CONTENT);
                chooseFile.setType("*/*");
                chooseFile = Intent.createChooser(chooseFile, "Choose a file");
                startActivityForResult(chooseFile, PICKFILE_RESULT_CODE);
            }
        });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            case PICKFILE_RESULT_CODE:
                if (resultCode == -1) {
                    fileUri = data.getData();
                    filePath = fileUri.getPath();
                    //tvItemPath.setText(filePath);
                    Log.d("FPATH", "" + fileUri);
                    //String filepath = Environment.getExternalStorageDirectory() + "/" + "HomeCric/Players/" + "r_sharma.png";
                    //Bitmap bitmap = BitmapFactory.decodeFile("" + fileUri);
                    tvitem.setImageURI(fileUri);
                    InputStream inputStream = null;
                    File f=null;
                    p_name=pname.getText().toString();
                    PlayersFaces pf = new PlayersFaces();
                    final String conv_pname = pf.convertPlayer(p_name);
                    try{
                        inputStream =getContext().getContentResolver().openInputStream(fileUri);
                        f= new File(Environment.getExternalStorageDirectory() + "/" + "HomeCric/Players/" +conv_pname+".png");
                        copyFile(inputStream, f);
                    }
                    catch(Exception e)
                    {Log.d("EXCEPTIO",""+e);}

                    //tvitem.setText(""+filePath);
                }
                break;
        }
    }

    public static void copyFile(InputStream sourceFile, File destFile) throws Exception{
        if (!destFile.getParentFile().exists())
            destFile.getParentFile().mkdirs();
        Log.d("DEST:",""+destFile.exists());
        if (!destFile.exists()) {
            destFile.createNewFile();
        }
        InputStream is = null; OutputStream os = null;
        is = sourceFile;
            os = new FileOutputStream(destFile);
            // buffer size 1K
            byte[] buf = new byte[1024];
            int bytesRead;
            while ((bytesRead = is.read(buf)) > 0)
            { os.write(buf, 0, bytesRead); }
           is.close();
           os.close();

    }
}