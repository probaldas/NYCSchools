package com.probaldas.nycSchools.ui.school.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.paging.PagedList;
import androidx.paging.PagedListAdapter;
import androidx.recyclerview.widget.DiffUtil;

import com.probaldas.nycSchools.databinding.SchoolListItemBinding;
import com.probaldas.nycSchools.models.SchoolDetails;

import java.util.Objects;

public class SchoolListPagedAdapter extends PagedListAdapter<SchoolDetails, SchoolPagedViewHolder> {

    public static final String SCHOOL_DETAILS = "SchoolDetails";
    private static DiffUtil.ItemCallback<SchoolDetails> diffCallback =
            new DiffUtil.ItemCallback<SchoolDetails>() {
                @Override
                public boolean areItemsTheSame(@NonNull SchoolDetails oldItem, @NonNull SchoolDetails newItem) {
                    return oldItem.getDbn().equals(newItem.getDbn());
                }

                @Override
                public boolean areContentsTheSame(@NonNull SchoolDetails oldItem, @NonNull SchoolDetails newItem) {
                    return oldItem.equals(newItem);
                }
            };
    private LayoutInflater inflater;
    private Context context;
    private PagedList<SchoolDetails> schoolDetails;

    public SchoolListPagedAdapter(Context context) {
        super(diffCallback);
        this.context = context;
    }

    @NonNull
    @Override
    public SchoolPagedViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (inflater == null) {
            inflater = LayoutInflater.from(parent.getContext());
        }

        SchoolListItemBinding itemBinding = SchoolListItemBinding.inflate(inflater, parent, false);
        return new SchoolPagedViewHolder(itemBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull SchoolPagedViewHolder holder, int position) {
        final SchoolViewModel schoolViewModel = getData(Objects.requireNonNull(getItem(position)));
        holder.bind(schoolViewModel);


    }

    private SchoolViewModel getData(SchoolDetails schoolDetails) {
        return new SchoolViewModel(schoolDetails.getSchoolName(),
                schoolDetails.getTotalStudents(),
                schoolDetails.getPrimaryAddressLine1(),
                schoolDetails.getCity(),
                schoolDetails.getStateCode(),
                schoolDetails.getZip());
    }
}
