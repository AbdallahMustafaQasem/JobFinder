package abdallah.job_finder.ui.Search;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.NetworkPolicy;
import com.squareup.picasso.Picasso;

import java.text.ParseException;
import java.util.List;

import abdallah.job_finder.R;
import abdallah.job_finder.data.General;
import abdallah.job_finder.ui.jobwebview.JobDetailsActivity;
import abdallah.job_finder.utils.Helper;

import static abdallah.job_finder.ui.jobwebview.JobDetailsActivity.KEY_LINK;
import static abdallah.job_finder.ui.jobwebview.JobDetailsActivity.KEY_NAME;


public class ResultAdapter extends RecyclerView.Adapter<ResultAdapter.ViewHolder> {

    private Context context;
    private List<General> itemList;

    public ResultAdapter(Context context, List<General> itemList) {
        this.context = context;
        this.itemList = itemList;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_item_result, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int i) {
        General result = itemList.get(i);
        if (result != null && result.getLogo() != null)
            Picasso.get().load(result.getLogo()).networkPolicy(NetworkPolicy.OFFLINE).placeholder(R.drawable.img_job).into(holder.imgCompany);
        else
            holder.imgCompany.setImageDrawable(context.getResources().getDrawable(R.drawable.img_job));

        if (result != null && result.getCompanyName() != null)
            holder.tvCompanyName.setText(result.getCompanyName() + "");
        holder.tvJob.setText(result.getJobTitle() + "");
        holder.tvLocation.setText(result.getLocation() + "");

        holder.tvProvider.setText(result.getProvider() + "");


        try {
            if (result.getProvider().equals("GitHub")) {
                holder.tvPostDate.setText(Helper.convertGitToNewFormat(result.getPostDate()));
            }else {
                holder.tvPostDate.setText(Helper.convertGovToNewFormat(result.getPostDate()));
            }

        } catch (ParseException e) {
            e.printStackTrace();
            holder.tvPostDate.setText(result.getPostDate() + "");
        }


        holder.itemView.setOnClickListener(v -> {
            Intent intent = new Intent(context, JobDetailsActivity.class);
            intent.putExtra(KEY_LINK, result.getUrl());
            intent.putExtra(KEY_NAME, result.getCompanyName());
            context.startActivity(intent);

        });
    }

    @Override
    public int getItemCount() {
        return itemList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView imgCompany;
        TextView tvCompanyName;
        TextView tvJob;
        TextView tvLocation;
        TextView tvPostDate;
        TextView tvProvider;

        public ViewHolder(View view) {
            super(view);
            imgCompany = view.findViewById(R.id.imgCompany);
            tvCompanyName = view.findViewById(R.id.tvCompanyName);
            tvJob = view.findViewById(R.id.tvJob);
            tvLocation = view.findViewById(R.id.tvLocation);
            tvPostDate = view.findViewById(R.id.tvPostDate);
            tvProvider = view.findViewById(R.id.tvProvider);
        }
    }


}
