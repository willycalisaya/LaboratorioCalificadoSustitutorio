import android.content.Context
import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.calisaya.willy.laboratoriocalificadosustitutorio.databinding.ItemPostBinding

class PostAdapter(private val context: Context, private val posts: List<Post>) :
    RecyclerView.Adapter<PostAdapter.PostViewHolder>() {

    inner class PostViewHolder(val binding: ItemPostBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostViewHolder {
        val binding = ItemPostBinding.inflate(LayoutInflater.from(context), parent, false)
        return PostViewHolder(binding)
    }

    override fun onBindViewHolder(holder: PostViewHolder, position: Int) {
        val post = posts[position]
        with(holder.binding) {
            tvTitle.text = post.title
            tvBody.text = post.body
        }

        holder.itemView.setOnClickListener {
            val smsIntent = Intent(Intent.ACTION_VIEW).apply {
                data = Uri.parse("sms:+51925137361")
                putExtra("sms_body", post.title)
            }
            context.startActivity(smsIntent)
        }

        holder.itemView.setOnLongClickListener {
            val emailIntent = Intent(Intent.ACTION_SEND).apply {
                type = "message/rfc822"
                putExtra(Intent.EXTRA_EMAIL, arrayOf("victor.saico@tecsup.edu.pe"))
                putExtra(Intent.EXTRA_SUBJECT, "Post Body")
                putExtra(Intent.EXTRA_TEXT, post.body)
            }
            context.startActivity(Intent.createChooser(emailIntent, "Send email"))
            true
        }
    }

    override fun getItemCount(): Int = posts.size
}
