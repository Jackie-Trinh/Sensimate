package com.example.sensimate.screens.survey
//
//import android.os.Bundle
//import android.view.LayoutInflater
//import android.view.View
//import android.view.View.NO_ID
//import android.view.ViewGroup
//import androidx.compose.animation.*
//import androidx.compose.animation.core.tween
//import androidx.compose.ui.platform.ComposeView
//import androidx.fragment.app.Fragment
//
//class SurveyFragment : Fragment() {
//
//
//    @OptIn(ExperimentalAnimationApi::class)
//    override fun onCreateView(
//        inflater: LayoutInflater,
//        container: ViewGroup?,
//        savedInstanceState: Bundle?
//    ): View {
//        return ComposeView(requireContext()).apply {
//            // In order for savedState to work, the same ID needs to be used for all instances.
//            id = NO_ID
//
//            layoutParams = ViewGroup.LayoutParams(
//                ViewGroup.LayoutParams.MATCH_PARENT,
//                ViewGroup.LayoutParams.MATCH_PARENT
//            )
//            setContent {
//
//                val state = viewModel.uiState.observeAsState().value ?: return@JetsurveyTheme
//                AnimatedContent(
//                    targetState = state,
//                    transitionSpec = {
//                        fadeIn() + slideIntoContainer(
//                            towards = AnimatedContentScope
//                                .SlideDirection.Up,
//                            animationSpec = tween(ANIMATION_SLIDE_IN_DURATION)
//                        ) with
//                                fadeOut(animationSpec = tween(ANIMATION_FADE_OUT_DURATION))
//                    }
//                ) { targetState ->
//                    // It's important to use targetState and not state, as its critical to ensure
//                    // a successful lookup of all the incoming and outgoing content during
//                    // content transform.
//                    when (targetState) {
//                        is SurveyState.Questions -> SurveyQuestionsScreen(
//                            questions = targetState,
//                            shouldAskPermissions = viewModel.askForPermissions,
//                            onAction = { id, action -> handleSurveyAction(id, action) },
//                            onDoNotAskForPermissions = { viewModel.doNotAskForPermissions() },
//                            onDonePressed = { viewModel.computeResult(targetState) },
//                            onBackPressed = {
//                                activity?.onBackPressedDispatcher?.onBackPressed()
//                            }
//                        )
//                        is SurveyState.Result -> SurveyResultScreen(
//                            result = targetState,
//                            onDonePressed = {
//                                activity?.onBackPressedDispatcher?.onBackPressed()
//                            }
//                        )
//                    }
//
//                }
//            }
//        }
//    }
//
//    companion object {
//        private const val ANIMATION_SLIDE_IN_DURATION = 600
//        private const val ANIMATION_FADE_OUT_DURATION = 200
//    }
//}