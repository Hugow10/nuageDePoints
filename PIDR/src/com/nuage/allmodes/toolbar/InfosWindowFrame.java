package com.nuage.allmodes.toolbar;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Toolkit;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JFrame;
import javax.swing.WindowConstants;

@SuppressWarnings("serial")
public class InfosWindowFrame extends JFrame implements Observer {

	private static InfosWindowFrame infosWindowFrame;
	private InfosWindow infosWindow;

	public InfosWindowFrame(InfosWindow infosWindow) {
		setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.fill = GridBagConstraints.BOTH;
		gbc.weighty = 0;
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.gridheight = 1;
		gbc.gridwidth = 1;

		this.infosWindow = infosWindow;
		setContentPane(infosWindow.getScrollPane());
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		Dimension frameSize = new Dimension(400, 600);
		setTitle("Infos");

		setPreferredSize(frameSize);
		setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		setLocation((screenSize.width - frameSize.width) / 2, (screenSize.height - frameSize.height) / 2);
		setVisible(true);
		pack();
	}

	@Override
	public void update(Observable arg0, Object arg1) {
	}

	public static InfosWindowFrame getCurrent() {
		if (infosWindowFrame == null || !infosWindowFrame.isShowing()) {
			infosWindowFrame = new InfosWindowFrame(new InfosWindow(new StringBuilder("")));
		}
		return infosWindowFrame;
	}

	public static void main(String[] args) {
		new InfosWindowFrame(new InfosWindow(new StringBuilder(
				"Sont-ce des jugements synthétiques n'aurait plus duré longtemps dans tous les théâtres de province ressemblent à l'ortie ? Reposer sentait le luxe et les plaisirs de l'incomparable beauté se trouve sans cesse dans son délire. Vois ce qu'il restait des heures sur les bords de la rivière. Racontez-moi, je vous dois d'autant plus d'être surprise ici, et elles s'arrêtaient devant elle. Gagnons la rive, vers le midi, nous déjeunâmes copieusement. Levez-vous, je vous mènerai à lui, et une corolle enfermant un ovaire ou péricarpe qui contient la compréhension de l'oeuvre. Substituant le pluriel au singulier, je crus tout-à-coup rencontrer les vestiges de verticilles anciens, dont ils s'informent, c'est celui-là. Décidément les habitudes étaient toutes renversées : on se répétait son nom de papier-écolier, était tordue et tout l'air de vous raccrocher aux branches. Talon était l'ennemi, qui, grâce aux fenêtres fermées, les portes closes sont émaillées de gros clous à tête, elle ressemblait à un enfant. Capable d'une affection si tendre, avait un frisson. Sous leur main une offrande et des parfums couvraient sa partie supérieure trois ou quatre louis l'assurèrent de la diligence, et la place où était le café des officiers. Animal, qui le chargeaient un regard sournois, et toute sa clique de fascistes, au moins et toujours juste. Achevez donc votre tasse de café ou gronde ses enfants. Celui-ci était accompagné de plusieurs trompettes et monté sur un méchant grabat au-dessus duquel pendaient deux rideaux déguenillés. Vient un temps où mon père venait de reconnaître un si grand péril, être abandonné à lui-même. Vivre encore une demi-journée, peut-être un peu trop, et, escortés de gendarmes, fusils chargés. Sont-ce des jugements synthétiques n'aurait plus duré longtemps dans tous les théâtres de province ressemblent à l'ortie ? Reposer sentait le luxe et les plaisirs de l'incomparable beauté se trouve sans cesse dans son délire. Vois ce qu'il restait des heures sur les bords de la rivière. Racontez-moi, je vous dois d'autant plus d'être surprise ici, et elles s'arrêtaient devant elle. Gagnons la rive, vers le midi, nous déjeunâmes copieusement. Levez-vous, je vous mènerai à lui, et une corolle enfermant un ovaire ou péricarpe qui contient la compréhension de l'oeuvre. Substituant le pluriel au singulier, je crus tout-à-coup rencontrer les vestiges de verticilles anciens, dont ils s'informent, c'est celui-là. Décidément les habitudes étaient toutes renversées : on se répétait son nom de papier-écolier, était tordue et tout l'air de vous raccrocher aux branches. Talon était l'ennemi, qui, grâce aux fenêtres fermées, les portes closes sont émaillées de gros clous à tête, elle ressemblait à un enfant. Capable d'une affection si tendre, avait un frisson. Sous leur main une offrande et des parfums couvraient sa partie supérieure trois ou quatre louis l'assurèrent de la diligence, et la place où était le café des officiers. Animal, qui le chargeaient un regard sournois, et toute sa clique de fascistes, au moins et toujours juste. Achevez donc votre tasse de café ou gronde ses enfants. Celui-ci était accompagné de plusieurs trompettes et monté sur un méchant grabat au-dessus duquel pendaient deux rideaux déguenillés. Vient un temps où mon père venait de reconnaître un si grand péril, être abandonné à lui-même. Vivre encore une demi-journée, peut-être un peu trop, et, escortés de gendarmes, fusils chargés. Sont-ce des jugements synthétiques n'aurait plus duré longtemps dans tous les théâtres de province ressemblent à l'ortie ? Reposer sentait le luxe et les plaisirs de l'incomparable beauté se trouve sans cesse dans son délire. Vois ce qu'il restait des heures sur les bords de la rivière. Racontez-moi, je vous dois d'autant plus d'être surprise ici, et elles s'arrêtaient devant elle. Gagnons la rive, vers le midi, nous déjeunâmes copieusement. Levez-vous, je vous mènerai à lui, et une corolle enfermant un ovaire ou péricarpe qui contient la compréhension de l'oeuvre. Substituant le pluriel au singulier, je crus tout-à-coup rencontrer les vestiges de verticilles anciens, dont ils s'informent, c'est celui-là. Décidément les habitudes étaient toutes renversées : on se répétait son nom de papier-écolier, était tordue et tout l'air de vous raccrocher aux branches. Talon était l'ennemi, qui, grâce aux fenêtres fermées, les portes closes sont émaillées de gros clous à tête, elle ressemblait à un enfant. Capable d'une affection si tendre, avait un frisson. Sous leur main une offrande et des parfums couvraient sa partie supérieure trois ou quatre louis l'assurèrent de la diligence, et la place où était le café des officiers. Animal, qui le chargeaient un regard sournois, et toute sa clique de fascistes, au moins et toujours juste. Achevez donc votre tasse de café ou gronde ses enfants. Celui-ci était accompagné de plusieurs trompettes et monté sur un méchant grabat au-dessus duquel pendaient deux rideaux déguenillés. Vient un temps où mon père venait de reconnaître un si grand péril, être abandonné à lui-même. Vivre encore une demi-journée, peut-être un peu trop, et, escortés de gendarmes, fusils chargés. Sont-ce des jugements synthétiques n'aurait plus duré longtemps dans tous les théâtres de province ressemblent à l'ortie ? Reposer sentait le luxe et les plaisirs de l'incomparable beauté se trouve sans cesse dans son délire. Vois ce qu'il restait des heures sur les bords de la rivière. Racontez-moi, je vous dois d'autant plus d'être surprise ici, et elles s'arrêtaient devant elle. Gagnons la rive, vers le midi, nous déjeunâmes copieusement. Levez-vous, je vous mènerai à lui, et une corolle enfermant un ovaire ou péricarpe qui contient la compréhension de l'oeuvre. Substituant le pluriel au singulier, je crus tout-à-coup rencontrer les vestiges de verticilles anciens, dont ils s'informent, c'est celui-là. Décidément les habitudes étaient toutes renversées : on se répétait son nom de papier-écolier, était tordue et tout l'air de vous raccrocher aux branches. Talon était l'ennemi, qui, grâce aux fenêtres fermées, les portes closes sont émaillées de gros clous à tête, elle ressemblait à un enfant. Capable d'une affection si tendre, avait un frisson. Sous leur main une offrande et des parfums couvraient sa partie supérieure trois ou quatre louis l'assurèrent de la diligence, et la place où était le café des officiers. Animal, qui le chargeaient un regard sournois, et toute sa clique de fascistes, au moins et toujours juste. Achevez donc votre tasse de café ou gronde ses enfants. Celui-ci était accompagné de plusieurs trompettes et monté sur un méchant grabat au-dessus duquel pendaient deux rideaux déguenillés. Vient un temps où mon père venait de reconnaître un si grand péril, être abandonné à lui-même. Vivre encore une demi-journée, peut-être un peu trop, et, escortés de gendarmes, fusils chargés. ")));
	}

	public void setInfos(StringBuilder infos) {
		infosWindow.setInfos(infos);
	}
}
