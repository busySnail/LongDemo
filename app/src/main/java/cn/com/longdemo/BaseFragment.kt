package cn.com.longdemo

import androidx.fragment.app.Fragment

class BaseFragment : Fragment(), IVisible {

    /**
     * 父节点Fragment是否对用户可见
     *
     * @return
     */
    private val isParentRealVisible: Boolean
        get() = if (parentFragment is IVisible) {
            (parentFragment as IVisible?)?.isRealVisible ?: false
        } else true
    override val isRealVisible: Boolean
        get() = isResumed && !isHidden && userVisibleHint && isParentRealVisible

    override fun setUserVisibleHint(isVisibleToUser: Boolean) {
        super.setUserVisibleHint(isVisibleToUser)
        onVisibleChange(isRealVisible)
    }

    override fun onHiddenChanged(hidden: Boolean) {
        super.onHiddenChanged(hidden)
        onVisibleChange(isRealVisible)
    }

    override fun onResume() {
        super.onResume()
        onVisibleChange(isRealVisible)
    }

    override fun onPause() {
        super.onPause()
        onVisibleChange(isRealVisible)
    }

    /**
     * 当onResume(),onPause(),onHiddenChanged(),setUserVisibleHint()调用后会触发onVisibleChange()回调，会被调用多次
     *
     * @param isVisible
     */
    override fun onVisibleChange(isVisible: Boolean) {

    }

    /**
     * 该页面是否有效，以此来判断是否可以更新UI.
     *
     * @return true:页面不再可用
     */
    fun isInvalid(): Boolean {
        return isRemoving || isDetached || activity == null || requireActivity().isFinishing
    }

}

interface IVisible {
    /**
     * 当前页面是否对用户可见
     *
     * @return
     */
    val isRealVisible: Boolean

    /**
     * 当前页面可见状态发生变化
     *
     * @param isVisible
     */
    fun onVisibleChange(isVisible: Boolean)
}