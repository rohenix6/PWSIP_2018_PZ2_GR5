<?php


namespace AppBundle\Menu;

use AppBundle\Entity\User;
use Knp\Menu\FactoryInterface;
use Symfony\Component\Security\Core\Authentication\Token\Storage\TokenStorage;
use Symfony\Component\Security\Core\Authorization\AuthorizationChecker;
use Symfony\Component\Security\Core\Security;
use UserBundle\Service\UserInformationService;

class MenuBuilder
{

    /**
     * @var FactoryInterface
     */
    private $factory;

    /**
     * @var TokenStorage
     */
    private $tokenStorage;

    /**
     * MenuBuilder constructor.
     * @param FactoryInterface $factory
     */
    public function __construct(FactoryInterface $factory, TokenStorage $tokenStorage)
    {
        $this->factory = $factory;
        $this->tokenStorage = $tokenStorage;
    }

    public function createMainMenu(array $options)
    {
        $menu = $this->factory->createItem('root', array(
            'childrenAttributes'    => array(
                'class'             => 'nav navbar-nav',
            ),
        ));

        $menu->addChild('Strona Główna', array('route' => 'home'));
        $user = $this->getCurrenctUser();
        if($user instanceof User) {
            $menu->addChild('Umów Wizytę', array('route' => 'visit'));
            $menu->addChild('Historia Wizyt', array('route' => 'history-visits'));
            $menu->addChild('Moje Wizyty', array('route' => 'my-visits'));

        }
        $menu->addChild('Kontakt', array('route' => 'contact'));
        $menu->addChild('Regulamin', array('route' => 'regulamin'));
        if($user instanceof User){
            if($user->hasRole("ROLE_ADMIN")){
                $menu->addChild('Panel admina', array('route' => 'admin'));
            }
            $menu->addChild('Wyloguj', array('route' => 'fos_user_security_logout'));
        } else {
            $menu->addChild('Załóż konto', array('route' => 'fos_user_registration_register'));
            $menu->addChild('Zaloguj', array('route' => 'fos_user_security_login'));
        }

        return $menu;
    }

    private function getCurrenctUser(){
        return $user = $this->tokenStorage->getToken()->getUser();
    }
}